package jbse.apps.run;

import static jbse.common.Type.className;

import java.util.HashSet;
import java.util.Iterator;
import java.util.SortedSet;

import jbse.bc.ClassHierarchy;
import jbse.bc.Signature;
import jbse.bc.exc.BadClassFileVersionException;
import jbse.bc.exc.ClassFileIllFormedException;
import jbse.bc.exc.ClassFileNotAccessibleException;
import jbse.bc.exc.ClassFileNotFoundException;
import jbse.bc.exc.IncompatibleClassFileException;
import jbse.bc.exc.WrongClassNameException;
import jbse.common.exc.UnexpectedInternalException;
import jbse.dec.DecisionProcedure;
import jbse.dec.DecisionProcedureAlgorithms;
import jbse.dec.exc.DecisionException;
import jbse.jvm.RunnerParameters;
import jbse.mem.State;
import jbse.mem.SwitchTable;
import jbse.mem.exc.FrozenStateException;
import jbse.tree.DecisionAlternative_XALOAD;
import jbse.tree.DecisionAlternative_XALOAD_Unresolved;
import jbse.tree.DecisionAlternative_XASTORE;
import jbse.tree.DecisionAlternative_XCMPY;
import jbse.tree.DecisionAlternative_IFX;
import jbse.tree.DecisionAlternative_XLOAD_GETX;
import jbse.tree.DecisionAlternative_XYLOAD_GETX_Unresolved;
import jbse.tree.DecisionAlternative_XYLOAD_GETX_Aliases;
import jbse.tree.DecisionAlternative_XYLOAD_GETX_Expands;
import jbse.tree.DecisionAlternative_XYLOAD_GETX_Null;
import jbse.tree.DecisionAlternative_XNEWARRAY;
import jbse.tree.DecisionAlternative_XSWITCH;
import jbse.val.Any;
import jbse.val.Calculator;
import jbse.val.Expression;
import jbse.val.PrimitiveSymbolicApply;
import jbse.val.PrimitiveSymbolicAtomic;
import jbse.val.NarrowingConversion;
import jbse.val.Operator;
import jbse.val.Primitive;
import jbse.val.PrimitiveVisitor;
import jbse.val.Reference;
import jbse.val.ReferenceConcrete;
import jbse.val.ReferenceSymbolic;
import jbse.val.Simplex;
import jbse.val.Symbolic;
import jbse.val.Term;
import jbse.val.Value;
import jbse.val.WideningConversion;
import jbse.val.exc.InvalidOperandException;
import jbse.val.exc.InvalidTypeException;

/**
 * {@link DecisionProcedureAlgorithms} for guided symbolic execution. 
 * It keeps a private JVM that runs a guiding concrete execution up to the 
 * concrete counterpart of the initial state, and filters all the decisions taken by 
 * the component decision procedure it decorates by evaluating the submitted clauses
 * in the state reached by the private JVM.
 */
public abstract class DecisionProcedureGuidance extends DecisionProcedureAlgorithms {
    private final JVM jvm;
    private final boolean nonStatic;
    private final HashSet<Object> seen = new HashSet<>();
    private boolean ended;    
    
    /**
     * Builds the {@link DecisionProcedureGuidance}.
     *
     * @param component the component {@link DecisionProcedure} it decorates.
     * @param calc a {@link Calculator}.
     * @param jvm a {@link JVM}.
     * @param numberOfHits an {@code int} greater or equal to one.
     * @throws GuidanceException if something fails during creation (and the caller
     *         is to blame).
     */
    public DecisionProcedureGuidance(DecisionProcedure component, Calculator calc, JVM jvm) 
    throws GuidanceException {
        super(component, calc);
        goFastAndImprecise(); //disables theorem proving of component until guidance ends
        this.jvm = jvm;
        this.nonStatic = this.jvm.isCurrentMethodNonStatic();
        this.ended = false;
    }
    
    /**
     * Ends guidance decision, and falls back on the 
     * component decision procedure.
     */
    public final void endGuidance() {
        this.ended = true;
        stopFastAndImprecise();
    }

    @Override
    protected final Outcome decide_IFX_Nonconcrete(ClassHierarchy hier, Primitive condition, SortedSet<DecisionAlternative_IFX> result) 
    throws DecisionException {

    	final Outcome retVal = super.decide_IFX_Nonconcrete(hier, condition, result);
        if (!this.ended) {
            try {
                final Iterator<DecisionAlternative_IFX> it = result.iterator();
                final Primitive conditionNot = condition.not();
                while (it.hasNext()) {
                    final DecisionAlternative_IFX da = it.next();
                    final Primitive conditionToCheck  = (da.value() ? condition : conditionNot);
                    final Primitive valueInConcreteState = this.jvm.eval(conditionToCheck);
                    if (valueInConcreteState != null && valueInConcreteState.surelyFalse()) {
                        it.remove();
                    }
                }
            } catch (InvalidTypeException e) {
                //this should never happen as arguments have been checked by the caller
                throw new UnexpectedInternalException(e);
            }
        }
        return retVal;
    }

    @Override
    protected final Outcome decide_XCMPY_Nonconcrete(ClassHierarchy hier, Primitive val1, Primitive val2, SortedSet<DecisionAlternative_XCMPY> result)
    throws DecisionException {
        final Outcome retVal = super.decide_XCMPY_Nonconcrete(hier, val1, val2, result);
        if (!this.ended) {
            try {
                final Primitive comparisonGT = val1.gt(val2);
                final Primitive comparisonEQ = val1.eq(val2);
                final Primitive comparisonLT = val1.lt(val2);
                final Iterator<DecisionAlternative_XCMPY> it = result.iterator();
                while (it.hasNext()) {
                    final DecisionAlternative_XCMPY da = it.next();
                    final Primitive conditionToCheck  = 
                        (da.operator() == Operator.GT ? comparisonGT :
                         da.operator() == Operator.EQ ? comparisonEQ :
                         comparisonLT);
                    final Primitive valueInConcreteState = this.jvm.eval(conditionToCheck);
                    if (valueInConcreteState != null && valueInConcreteState.surelyFalse()) {
                        it.remove();
                    }
                }
            } catch (InvalidTypeException | InvalidOperandException e) {
                //this should never happen as arguments have been checked by the caller
                throw new UnexpectedInternalException(e);
            }
        }
        return retVal;
    }

    @Override
    protected final Outcome decide_XSWITCH_Nonconcrete(ClassHierarchy hier, Primitive selector, SwitchTable tab, SortedSet<DecisionAlternative_XSWITCH> result)
    throws DecisionException {
        final Outcome retVal = super.decide_XSWITCH_Nonconcrete(hier, selector, tab, result);
        if (!this.ended) {
            try {
                final Iterator<DecisionAlternative_XSWITCH> it = result.iterator();
                while (it.hasNext()) {
                    final DecisionAlternative_XSWITCH da = it.next();
                    final Primitive conditionToCheck;
                    conditionToCheck = (da.isDefault() ?
                                        tab.getDefaultClause(selector) :
                                        selector.eq(this.calc.valInt(da.value())));
                    final Primitive valueInConcreteState = this.jvm.eval(conditionToCheck);
                    if (valueInConcreteState != null && valueInConcreteState.surelyFalse()) {
                        it.remove();
                    }
                }
            } catch (InvalidOperandException | InvalidTypeException e) {
                //this should never happen as arguments have been checked by the caller
                throw new UnexpectedInternalException(e);
            }
        }
        return retVal;
    }

    @Override
    protected final Outcome decide_XNEWARRAY_Nonconcrete(ClassHierarchy hier, Primitive countsNonNegative, SortedSet<DecisionAlternative_XNEWARRAY> result)
    throws DecisionException {
        final Outcome retVal = super.decide_XNEWARRAY_Nonconcrete(hier, countsNonNegative, result);
        if (!this.ended) {
            try {
                final Iterator<DecisionAlternative_XNEWARRAY> it = result.iterator();
                while (it.hasNext()) {
                    final DecisionAlternative_XNEWARRAY da = it.next();
                    final Primitive conditionToCheck = (da.ok() ? countsNonNegative : countsNonNegative.not());
                    final Primitive valueInConcreteState = this.jvm.eval(conditionToCheck);
                    if (valueInConcreteState != null && valueInConcreteState.surelyFalse()) {
                        it.remove();
                    }
                }
            } catch (InvalidTypeException e) {
                //this should never happen as arguments have been checked by the caller
                throw new UnexpectedInternalException(e);
            }
        }
        return retVal;
    }

    @Override
    protected final Outcome decide_XASTORE_Nonconcrete(ClassHierarchy hier, Primitive inRange, SortedSet<DecisionAlternative_XASTORE> result)
    throws DecisionException {
        final Outcome retVal = super.decide_XASTORE_Nonconcrete(hier, inRange, result);
        if (!this.ended) {
            try {
                final Iterator<DecisionAlternative_XASTORE> it = result.iterator();
                while (it.hasNext()) {
                    final DecisionAlternative_XASTORE da = it.next();
                    final Primitive conditionToCheck = (da.isInRange() ? inRange : inRange.not());
                    final Primitive valueInConcreteState = this.jvm.eval(conditionToCheck);
                    if (valueInConcreteState != null && valueInConcreteState.surelyFalse()) {
                        it.remove();
                    }
                }
            } catch (InvalidTypeException e) {
                //this should never happen as arguments have been checked by the caller
                throw new UnexpectedInternalException(e);
            }
        }
        return retVal;
    }

    @Override
    protected final Outcome resolve_XLOAD_GETX_Unresolved(State state, ReferenceSymbolic refToLoad, SortedSet<DecisionAlternative_XLOAD_GETX> result)
    throws DecisionException, ClassFileNotFoundException, ClassFileIllFormedException, 
    BadClassFileVersionException, WrongClassNameException, 
    IncompatibleClassFileException, ClassFileNotAccessibleException {
        updateExpansionBackdoor(state, refToLoad);
        final Outcome retVal = super.resolve_XLOAD_GETX_Unresolved(state, refToLoad, result);
        if (!this.ended) {
            final Iterator<DecisionAlternative_XLOAD_GETX> it = result.iterator();
            while (it.hasNext()) {
                final DecisionAlternative_XYLOAD_GETX_Unresolved dar = (DecisionAlternative_XYLOAD_GETX_Unresolved) it.next();
                filter(state, refToLoad, dar, it);
            }
        }
        return retVal;
    }

    @Override
    protected final Outcome resolve_XALOAD_ResolvedNonconcrete(ClassHierarchy hier, Expression accessExpression, Value valueToLoad, boolean fresh, Reference arrayToWriteBack,SortedSet<DecisionAlternative_XALOAD> result)
    throws DecisionException {
        final Outcome retVal = super.resolve_XALOAD_ResolvedNonconcrete(hier, accessExpression, valueToLoad, fresh, arrayToWriteBack, result);
        if (!this.ended) {
            final Iterator<DecisionAlternative_XALOAD> it = result.iterator();
            while (it.hasNext()) {
                final DecisionAlternative_XALOAD da = it.next();
                final Primitive conditionToCheck = da.getArrayAccessExpression();
                final Primitive valueInConcreteState = this.jvm.eval(conditionToCheck);
                if (valueInConcreteState != null && valueInConcreteState.surelyFalse()) {
                    it.remove();
                }
            }
        }
        return retVal;
    }

    @Override
    protected final Outcome resolve_XALOAD_Unresolved(State state, Expression accessExpression, ReferenceSymbolic refToLoad, boolean fresh, Reference arrayReference, SortedSet<DecisionAlternative_XALOAD> result)
    throws DecisionException, ClassFileNotFoundException, ClassFileIllFormedException, 
    BadClassFileVersionException, WrongClassNameException, 
    IncompatibleClassFileException, ClassFileNotAccessibleException {
        updateExpansionBackdoor(state, refToLoad);
        final Outcome retVal = super.resolve_XALOAD_Unresolved(state, accessExpression, refToLoad, fresh, arrayReference, result);
        if (!this.ended) {
            final Iterator<DecisionAlternative_XALOAD> it = result.iterator();
            while (it.hasNext()) {
                final DecisionAlternative_XALOAD_Unresolved dar = (DecisionAlternative_XALOAD_Unresolved) it.next();
                final Primitive conditionToCheck = dar.getArrayAccessExpression();
                final Primitive valueInConcreteState = this.jvm.eval(conditionToCheck);
                if (valueInConcreteState != null && valueInConcreteState.surelyFalse()) {
                    it.remove();
                } else {
                    filter(state, refToLoad, dar, it);
                }
            }
        }
        return retVal;
    }

    private void updateExpansionBackdoor(State state, ReferenceSymbolic refToLoad) throws GuidanceException {
        final String refType = className(refToLoad.getStaticType());
        final String objType = this.jvm.typeOfObject(refToLoad);
        if (objType != null && !refType.equals(objType)) {
            state.getClassHierarchy().addToExpansionBackdoor(refType, objType);
        }
    }

    private void filter(State state, ReferenceSymbolic refToLoad, DecisionAlternative_XYLOAD_GETX_Unresolved dar, Iterator<?> it) 
    throws GuidanceException {
        if (dar instanceof DecisionAlternative_XYLOAD_GETX_Null && !this.jvm.isNull(refToLoad)) {
            it.remove();
        } else if (dar instanceof DecisionAlternative_XYLOAD_GETX_Aliases) {
            final DecisionAlternative_XYLOAD_GETX_Aliases dara = (DecisionAlternative_XYLOAD_GETX_Aliases) dar;
            final ReferenceSymbolic aliasOrigin;
			try {
				aliasOrigin = state.getObject(new ReferenceConcrete(dara.getObjectPosition())).getOrigin();
			} catch (FrozenStateException e) {
				//this should never happen
				throw new UnexpectedInternalException(e);
			}
            if (!this.jvm.areAlias(refToLoad, aliasOrigin)) {
                it.remove();
            }
        } else if (dar instanceof DecisionAlternative_XYLOAD_GETX_Expands) {
            final DecisionAlternative_XYLOAD_GETX_Expands dare = (DecisionAlternative_XYLOAD_GETX_Expands) dar;
            if (this.jvm.isNull(refToLoad) || alreadySeen(refToLoad) ||
               !dare.getClassFileOfTargetObject().getClassName().equals(this.jvm.typeOfObject(refToLoad))) {
                it.remove();
            } else {
                markAsSeen(refToLoad);
            }
        }
    }
    
    private boolean alreadySeen(ReferenceSymbolic m) throws GuidanceException {
        if (this.nonStatic && "{ROOT}:this".equals(m.asOriginString())) {
            return true;
        }
        return this.seen.contains(this.jvm.getValue(m));
    }
    
    private void markAsSeen(ReferenceSymbolic m) throws GuidanceException {
        this.seen.add(this.jvm.getValue(m));
    }
    
    @Override
    public void close() throws DecisionException {
    	super.close();
    	this.jvm.close();
    }
    
    public static abstract class JVM {
        protected final Calculator calc;

        /**
         * Constructor. The subclass constructor must launch
         * a JVM and run it until the execution hits the method
         * with signature {@code stopSignature} for {@code numberOfHits}
         * times.
         * 
         * @param calc a {@link Calculator}
         * @param runnerParameters the {@link RunnerParameters} with information
         *        about the classpath and the method to run.
         * @param stopSignature the {@link Signature} of the method where to stop
         *        execution.
         * @param numberOfHits an {@code int}, the number of times {@code stopSignature}
         *        must be hit for the execution to stop.
         * @throws GuidanceException if something goes wrong while the JVM runs.
         */
        public JVM(Calculator calc, RunnerParameters runnerParameters, Signature stopSignature, int numberOfHits) 
        throws GuidanceException {
            if (numberOfHits < 1) {
                throw new GuidanceException("Invalid number of hits " + numberOfHits + ".");
            }
            this.calc = calc;
        }
        
        /**
         * Checks if the current method in the reached concrete state
         * is (not) static.
         * 
         * @return {@code true} iff the current method is not static. 
         * @throws GuidanceException if something goes wrong.
         */
        public abstract boolean isCurrentMethodNonStatic() throws GuidanceException;
        
        /**
         * Returns the class of an object in the reached concrete state.
         * 
         * @param origin the {@link ReferenceSymbolic} to the object.
         * @return a {@link String}, the class of the object referred to
         *         by {@code origin}, or {@code null} if {@code origin}
         *         points to {@code null}.
         * @throws GuidanceException if {@code origin} does not refer to an object.
         */
        public abstract String typeOfObject(ReferenceSymbolic origin) throws GuidanceException;

        /**
         * Returns whether a {@link ReferenceSymbolic} points to {@code null} in the reached 
         * concrete state.
         * 
         * @param origin a {@link ReferenceSymbolic}.
         * @return {@code true} iff {@code origin} points to {@code null}.
         * @throws GuidanceException if {@code origin} does not refer to an object.
         */
        public abstract boolean isNull(ReferenceSymbolic origin) throws GuidanceException;

        /**
         * Returns whether two different {@link ReferenceSymbolic}s refer to the same object
         * in the reached concrete state.
         * 
         * @param first a {@link ReferenceSymbolic}.
         * @param second a {@link ReferenceSymbolic}.
         * @return {@code true} iff {@code first} and {@code second} refer to the same
         *         object, or both refer to {@code null}.
         * @throws GuidanceException if {@code first} or {@code second} does not refer 
         *         to an object.
         */
        public abstract boolean areAlias(ReferenceSymbolic first, ReferenceSymbolic second) throws GuidanceException;

        /**
         * Returns the concrete value in the reached concrete state 
         * for a {@link Symbolic} value in the symbolic state.
         * 
         * @param origin a {@link Symbolic}.
         * @return a {@link Primitive} if {@code origin} is also {@link Primitive}, 
         *         otherwise a subclass-dependent object that "stands for" 
         *         the referred object, that must satisfy the property that, 
         *         if {@link #areAlias(ReferenceSymbolic, ReferenceSymbolic) areAlias}{@code (first, second)}, 
         *         then {@link #getValue(Symbolic) getValue}{@code (first).}{@link Object#equals(Object) equals}{@code (}{@link #getValue(Symbolic) getValue}{@code (second))}.
         * @throws GuidanceException if {@code origin} does not refer to an object.
         */
        public abstract Object getValue(Symbolic origin) throws GuidanceException;
        
        /**
         * Evaluates a {@link Primitive} in the reached concrete state.
         * 
         * @param toEval a {@link Primitive}.
         * @return the {@link Primitive} corresponding to the concrete
         *         value of {@code toEval} in the reached concrete state
         *         (if {@code toEval instanceof }{@link Simplex} then
         *         the method will return {@code toEval}).
         * @throws GuidanceException
         */
        public final Primitive eval(Primitive toEval) throws GuidanceException {
            final Evaluator evaluator = new Evaluator(this.calc, this);
            try {
                toEval.accept(evaluator);
            } catch (RuntimeException | GuidanceException e) {
                //do not stop them
                throw e;
            } catch (Exception e) {
                //should not happen
                throw new UnexpectedInternalException(e);
            }
            return evaluator.value;
        }

        private static final class Evaluator implements PrimitiveVisitor {
            private final Calculator calc;
            private final JVM jvm;
            Primitive value; //the result

            public Evaluator(Calculator calc, JVM jvm) {
                this.calc = calc;
                this.jvm = jvm;
            }

            @Override
            public void visitAny(Any x) {
                this.value = x;
            }

            @Override
            public void visitExpression(Expression e) throws Exception {
                if (e.isUnary()) {
                    e.getOperand().accept(this);
                    final Primitive operandValue = this.value;
                    if (operandValue == null) {
                        this.value = null;
                        return;
                    }
                    this.value = this.calc.applyUnary(e.getOperator(), operandValue);
                } else {
                    e.getFirstOperand().accept(this);
                    final Primitive firstOperandValue = this.value;
                    if (firstOperandValue == null) {
                        this.value = null;
                        return;
                    }
                    e.getSecondOperand().accept(this);
                    final Primitive secondOperandValue = this.value;
                    if (secondOperandValue == null) {
                        this.value = null;
                        return;
                    }
                    this.value = this.calc.applyBinary(firstOperandValue, e.getOperator(), secondOperandValue);
                }
            }

            @Override
            public void visitPrimitiveSymbolicApply(PrimitiveSymbolicApply x) throws Exception {
                final Object funValue = this.jvm.getValue(x);
                if (funValue instanceof Primitive) {
                    this.value = (Primitive) funValue;
                } else {
                    this.value = null;
                }
            }

            @Override
            public void visitPrimitiveSymbolicAtomic(PrimitiveSymbolicAtomic s) throws GuidanceException {
                final Object fieldValue = this.jvm.getValue(s);
                if (fieldValue instanceof Primitive) {
                    this.value = (Primitive) fieldValue;
                } else {
                    this.value = null;
                }
            }

            @Override
            public void visitSimplex(Simplex x) {
                this.value = x;
            }

            @Override
            public void visitTerm(Term x) {
                this.value = x;
            }

            @Override
            public void visitNarrowingConversion(NarrowingConversion x) throws Exception {
                x.getArg().accept(this);
                this.value = this.calc.narrow(x.getType(), this.value);
            }

            @Override
            public void visitWideningConversion(WideningConversion x) throws Exception {
                x.getArg().accept(this);
                this.value = (x.getType() == this.value.getType() ? this.value : this.calc.widen(x.getType(), this.value));
                //note that the concrete this.value could already be widened
                //because of conversion of actual types to computational types
                //through operand stack, see JVMSpec 2.11.1, tab. 2.3
            }
        }

        protected void close() { }
    }
}
