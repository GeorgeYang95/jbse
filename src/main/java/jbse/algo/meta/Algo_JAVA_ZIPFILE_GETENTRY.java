package jbse.algo.meta;

import static jbse.algo.Util.exitFromAlgorithm;
import static jbse.algo.Util.failExecution;
import static jbse.algo.Util.throwNew;
import static jbse.algo.Util.throwVerifyError;
import static jbse.common.Type.internalClassName;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;
import java.util.zip.ZipFile;

import jbse.algo.Algo_INVOKEMETA_Nonbranching;
import jbse.algo.InterruptException;
import jbse.algo.StrategyUpdate;
import jbse.algo.exc.SymbolicValueNotAllowedException;
import jbse.algo.meta.exc.UndefinedResultException;
import jbse.common.exc.ClasspathException;
import jbse.common.exc.UnexpectedInternalException;
import jbse.mem.Array;
import jbse.mem.State;
import jbse.mem.Array.AccessOutcomeInValue;
import jbse.mem.exc.FastArrayAccessNotAllowedException;
import jbse.tree.DecisionAlternative_NONE;
import jbse.val.Calculator;
import jbse.val.Primitive;
import jbse.val.Reference;
import jbse.val.Simplex;
import jbse.val.exc.InvalidOperandException;
import jbse.val.exc.InvalidTypeException;

/**
 * Meta-level implementation of {@link java.util.zip.ZipFile#getEntry(long, byte[], boolean)}.
 * 
 * @author Pietro Braione
 */
public final class Algo_JAVA_ZIPFILE_GETENTRY extends Algo_INVOKEMETA_Nonbranching {
    private Simplex toPush; //set by cookMore
    
    @Override
    protected Supplier<Integer> numOperands() {
        return () -> 3;
    }

    @Override
    protected void cookMore(State state) 
    throws InterruptException, ClasspathException, SymbolicValueNotAllowedException, UndefinedResultException {
        try {
            //gets the first (long jzfile) parameter
            final Primitive _jzfile = (Primitive) this.data.operand(0);
            if (_jzfile.isSymbolic()) {
                throw new SymbolicValueNotAllowedException("The long jzfile parameter to invocation of method java.util.zip.ZipFile.getEntry cannot be a symbolic value.");
            }
            final long jzfile = ((Long) ((Simplex) _jzfile).getActualValue()).longValue();
            //TODO check that jzfile is open, and throw UndefinedResultException in the negative case
            
            //gets the second (byte[] name) parameter
            final Reference nameRef = (Reference) this.data.operand(1);
            if (state.isNull(nameRef)) {
                throw new UndefinedResultException("Invoked method java.util.zip.ZipFile.getEntry with null byte[] name parameter.");
            }
            final Array _name = (Array) state.getObject(nameRef);
            if (_name == null) {
                throw new UnexpectedInternalException("Invocation of method java.util.zip.ZipFile.getEntry has an unresolved symbolic reference as its byte[] name parameter.");
            }
            if (!_name.isSimple()) {
                throw new SymbolicValueNotAllowedException("The byte[] name parameter to invocation of method java.util.zip.ZipFile.getEntry cannot be an array that is not simple.");
            }
            final int nameLength = ((Integer) ((Simplex) _name.getLength()).getActualValue()).intValue();
            final byte[] name = new byte[nameLength];
            final Calculator calc = state.getCalculator();
            for (int i = 0; i < nameLength; ++i) {
                final Simplex name_i = (Simplex) ((AccessOutcomeInValue) _name.getFast(calc.valInt(i))).getValue();
                name[i] = ((Byte) name_i.getActualValue()).byteValue();
            }
            
            //gets the third (boolean addSlash) parameter
            final Primitive _addSlash = (Primitive) this.data.operand(2);
            if (_addSlash.isSymbolic()) {
                throw new SymbolicValueNotAllowedException("The boolean addSlash parameter to invocation of method java.util.zip.ZipFile.getEntry method cannot be a symbolic value.");
            }
            final boolean addSlash = (((Integer) ((Simplex) _addSlash).getActualValue()).intValue() > 0);
            
            
            //invokes metacircularly the getEntry method
            final Method method = ZipFile.class.getDeclaredMethod("getEntry", long.class, byte[].class, boolean.class);
            method.setAccessible(true);
            final long retVal = (long) method.invoke(null, jzfile, name, addSlash);
            this.toPush = state.getCalculator().valLong(retVal);
        } catch (InvocationTargetException e) {
            final String cause = internalClassName(e.getCause().getClass().getName());
            throwNew(state, cause);
            exitFromAlgorithm();
        } catch (ClassCastException e) {
            throwVerifyError(state);
            exitFromAlgorithm();
        } catch (SecurityException | NoSuchMethodException | IllegalAccessException | 
                 FastArrayAccessNotAllowedException | InvalidTypeException | InvalidOperandException e) {
            //this should not happen
            failExecution(e);
        }
    }

    @Override
    protected StrategyUpdate<DecisionAlternative_NONE> updater() {
        return (state, alt) -> {
            state.pushOperand(this.toPush);
        };
    }
}
