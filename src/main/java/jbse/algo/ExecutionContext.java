package jbse.algo;

import static jbse.algo.Overrides.ALGO_INVOKEMETA_METACIRCULAR;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETCOMPONENTTYPE;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_FORNAME0;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETDECLAREDCONSTRUCTORS0;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETDECLAREDFIELDS0;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETDECLARINGCLASS0;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETENCLOSINGMETHOD0;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETMODIFIERS;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETNAME0;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETPRIMITIVECLASS;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_GETSUPERCLASS;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_ISARRAY;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_ISASSIGNABLEFROM;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_ISINSTANCE;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_ISINTERFACE;
import static jbse.algo.Overrides.ALGO_JAVA_CLASS_ISPRIMITIVE;
import static jbse.algo.Overrides.ALGO_JAVA_CLASSLOADER_FINDBOOTSTRAPCLASS;
import static jbse.algo.Overrides.ALGO_JAVA_CLASSLOADER_FINDLOADEDCLASS0;
import static jbse.algo.Overrides.ALGO_JAVA_CLASSLOADER_NATIVELIBRARY_LOAD;
import static jbse.algo.Overrides.ALGO_JAVA_FILEINPUTSTREAM_AVAILABLE;
import static jbse.algo.Overrides.ALGO_JAVA_FILEINPUTSTREAM_CLOSE0;
import static jbse.algo.Overrides.ALGO_JAVA_FILEINPUTSTREAM_OPEN0;
import static jbse.algo.Overrides.ALGO_JAVA_FILEINPUTSTREAM_READBYTES;
import static jbse.algo.Overrides.ALGO_JAVA_METHODHANDLENATIVES_RESOLVE;
import static jbse.algo.Overrides.ALGO_JAVA_OBJECT_CLONE;
import static jbse.algo.Overrides.ALGO_JAVA_OBJECT_GETCLASS;
import static jbse.algo.Overrides.ALGO_JAVA_OBJECT_HASHCODE;
import static jbse.algo.Overrides.ALGO_JAVA_REFLECT_ARRAY_NEWARRAY;
import static jbse.algo.Overrides.ALGO_JAVA_STRING_HASHCODE;
import static jbse.algo.Overrides.ALGO_JAVA_STRING_INTERN;
import static jbse.algo.Overrides.ALGO_JAVA_STRINGBUILDER_APPEND;
import static jbse.algo.Overrides.ALGO_JAVA_SYSTEM_ARRAYCOPY;
import static jbse.algo.Overrides.ALGO_JAVA_SYSTEM_IDENTITYHASHCODE;
import static jbse.algo.Overrides.ALGO_JAVA_SYSTEM_MAPLIBRARYNAME;
import static jbse.algo.Overrides.ALGO_JAVA_SYSTEM_SETERR0;
import static jbse.algo.Overrides.ALGO_JAVA_SYSTEM_SETIN0;
import static jbse.algo.Overrides.ALGO_JAVA_SYSTEM_SETOUT0;
import static jbse.algo.Overrides.ALGO_JAVA_THREAD_CURRENTTHREAD;
import static jbse.algo.Overrides.ALGO_JAVA_THROWABLE_FILLINSTACKTRACE;
import static jbse.algo.Overrides.ALGO_JAVA_THROWABLE_GETSTACKTRACEDEPTH;
import static jbse.algo.Overrides.ALGO_JAVA_THROWABLE_GETSTACKTRACEELEMENT;
import static jbse.algo.Overrides.ALGO_JAVA_XFILESYSTEM_CANONICALIZE0;
import static jbse.algo.Overrides.ALGO_JAVA_XFILESYSTEM_GETBOOLEANATTRIBUTESX;
import static jbse.algo.Overrides.ALGO_JAVA_XFILESYSTEM_GETLASTMODIFIEDTIME;
import static jbse.algo.Overrides.ALGO_JAVA_XFILESYSTEM_LIST;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_ANY;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_ASSUMECLASSNOTINITIALIZED;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_ENDGUIDANCE;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_FAIL;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_IGNORE;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_ISRESOLVED;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_ISSYMBOLIC;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_SUCCEED;
import static jbse.algo.Overrides.ALGO_JBSE_ANALYSIS_SYMBOLNAME;
import static jbse.algo.Overrides.ALGO_SUN_NATIVECONSTRUCTORACCESSORIMPL_NEWINSTANCE0;
import static jbse.algo.Overrides.ALGO_SUN_REFLECTION_GETCALLERCLASS;
import static jbse.algo.Overrides.ALGO_SUN_REFLECTION_GETCLASSACCESSFLAGS;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_ALLOCATEMEMORY;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_COMPAREANDSWAPINT;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_COMPAREANDSWAPLONG;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_COMPAREANDSWAPOBJECT;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_DEFINEANONYMOUSCLASS;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_ENSURECLASSINITIALIZED;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_FREEMEMORY;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_GETBYTE;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_GETINTVOLATILE;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_GETLONG;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_GETOBJECTVOLATILE;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_OBJECTFIELDOFFSET;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_PUTLONG;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_PUTOBJECTVOLATILE;
import static jbse.algo.Overrides.ALGO_SUN_UNSAFE_SHOULDBEINITIALIZED;
import static jbse.algo.Overrides.ALGO_noclass_REGISTERLOADEDCLASS;
import static jbse.algo.Overrides.ALGO_noclass_REGISTERMETHODTYPE;
import static jbse.algo.Overrides.ALGO_noclass_SETPHASEPOSTINIT;
import static jbse.algo.Overrides.ALGO_noclass_SETSTANDARDCLASSLOADERSREADY;
import static jbse.algo.Overrides.ALGO_noclass_STORELINKEDMETHODANDAPPENDIX;
import static jbse.algo.Overrides.BASE_JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION_1;
import static jbse.algo.Overrides.BASE_JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION_2;
import static jbse.algo.Overrides.BASE_JAVA_ACCESSCONTROLLER_DOPRIVILEGED_NOEXCEPTION;
import static jbse.algo.Overrides.BASE_JAVA_ACCESSCONTROLLER_GETSTACKACCESSCONTROLCONTEXT;
import static jbse.algo.Overrides.BASE_JAVA_ATOMICLONG_VMSUPPORTSCS8;
import static jbse.algo.Overrides.BASE_JAVA_CLASS_DESIREDASSERTIONSTATUS0;
import static jbse.algo.Overrides.BASE_JAVA_CLASSLOADER_FINDBUILTINLIB;
import static jbse.algo.Overrides.BASE_JAVA_METHODHANDLENATIVES_GETCONSTANT;
import static jbse.algo.Overrides.BASE_JAVA_RUNTIME_AVAILABLEPROCESSORS;
import static jbse.algo.Overrides.BASE_JAVA_SYSTEM_INITPROPERTIES;
import static jbse.algo.Overrides.BASE_JAVA_THREAD_ISALIVE;
import static jbse.algo.Overrides.BASE_JBSE_ANALYSIS_ISRUNBYJBSE;
import static jbse.algo.Overrides.BASE_SUN_SIGNAL_FINDSIGNAL;
import static jbse.algo.Overrides.BASE_SUN_SIGNAL_HANDLE0;
import static jbse.algo.Overrides.BASE_SUN_UNSAFE_ADDRESSSIZE;
import static jbse.algo.Overrides.BASE_SUN_UNSAFE_ARRAYBASEOFFSET;
import static jbse.algo.Overrides.BASE_SUN_UNSAFE_ARRAYINDEXSCALE;
import static jbse.algo.Overrides.BASE_SUN_URLCLASSPATH_GETLOOKUPCACHEURLS;
import static jbse.bc.ClassLoaders.CLASSLOADER_BOOT;
import static jbse.bc.Signatures.JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION_1;
import static jbse.bc.Signatures.JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION_2;
import static jbse.bc.Signatures.JAVA_ACCESSCONTROLLER_DOPRIVILEGED_NOEXCEPTION;
import static jbse.bc.Signatures.JAVA_ACCESSCONTROLLER_GETSTACKACCESSCONTROLCONTEXT;
import static jbse.bc.Signatures.JAVA_ATOMICLONG_VMSUPPORTSCS8;
import static jbse.bc.Signatures.JAVA_BYTE_BYTECACHE;
import static jbse.bc.Signatures.JAVA_CHARACTER_CHARACTERCACHE;
import static jbse.bc.Signatures.JAVA_CHARSET_EXTENDEDPROVIDERHOLDER;
import static jbse.bc.Signatures.JAVA_CLASS_DESIREDASSERTIONSTATUS0;
import static jbse.bc.Signatures.JAVA_CLASS_FORNAME0;
import static jbse.bc.Signatures.JAVA_CLASS_GETCOMPONENTTYPE;
import static jbse.bc.Signatures.JAVA_CLASS_GETDECLAREDCONSTRUCTORS0;
import static jbse.bc.Signatures.JAVA_CLASS_GETDECLAREDFIELDS0;
import static jbse.bc.Signatures.JAVA_CLASS_GETDECLARINGCLASS0;
import static jbse.bc.Signatures.JAVA_CLASS_GETENCLOSINGMETHOD0;
import static jbse.bc.Signatures.JAVA_CLASS_GETMODIFIERS;
import static jbse.bc.Signatures.JAVA_CLASS_GETNAME0;
import static jbse.bc.Signatures.JAVA_CLASS_GETPRIMITIVECLASS;
import static jbse.bc.Signatures.JAVA_CLASS_GETSUPERCLASS;
import static jbse.bc.Signatures.JAVA_CLASS_ISARRAY;
import static jbse.bc.Signatures.JAVA_CLASS_ISASSIGNABLEFROM;
import static jbse.bc.Signatures.JAVA_CLASS_ISINSTANCE;
import static jbse.bc.Signatures.JAVA_CLASS_ISINTERFACE;
import static jbse.bc.Signatures.JAVA_CLASS_ISPRIMITIVE;
import static jbse.bc.Signatures.JAVA_CLASS_REGISTERNATIVES;
import static jbse.bc.Signatures.JAVA_CLASSLOADER_FINDBOOTSTRAPCLASS;
import static jbse.bc.Signatures.JAVA_CLASSLOADER_FINDBUILTINLIB;
import static jbse.bc.Signatures.JAVA_CLASSLOADER_FINDLOADEDCLASS0;
import static jbse.bc.Signatures.JAVA_CLASSLOADER_NATIVELIBRARY_LOAD;
import static jbse.bc.Signatures.JAVA_CLASSLOADER_REGISTERNATIVES;
import static jbse.bc.Signatures.JAVA_DOUBLE_DOUBLETORAWLONGBITS;
import static jbse.bc.Signatures.JAVA_DOUBLE_LONGBITSTODOUBLE;
import static jbse.bc.Signatures.JAVA_ENUM;
import static jbse.bc.Signatures.JAVA_FILEDESCRIPTOR_INITIDS;
import static jbse.bc.Signatures.JAVA_FILEINPUTSTREAM_INITIDS;
import static jbse.bc.Signatures.JAVA_FILEINPUTSTREAM_AVAILABLE;
import static jbse.bc.Signatures.JAVA_FILEINPUTSTREAM_CLOSE0;
import static jbse.bc.Signatures.JAVA_FILEINPUTSTREAM_OPEN0;
import static jbse.bc.Signatures.JAVA_FILEINPUTSTREAM_READBYTES;
import static jbse.bc.Signatures.JAVA_FILEOUTPUTSTREAM_INITIDS;
import static jbse.bc.Signatures.JAVA_FLOAT_FLOATTORAWINTBITS;
import static jbse.bc.Signatures.JAVA_IDENTITYHASHMAP;
import static jbse.bc.Signatures.JAVA_INVOKERBYTECODEGENERATOR_2;
import static jbse.bc.Signatures.JAVA_LAMBDAFORM;
import static jbse.bc.Signatures.JAVA_LAMBDAFORM_NAME;
import static jbse.bc.Signatures.JAVA_LINKEDLIST;
import static jbse.bc.Signatures.JAVA_LINKEDLIST_ENTRY;
import static jbse.bc.Signatures.JAVA_METHODHANDLENATIVES_GETCONSTANT;
import static jbse.bc.Signatures.JAVA_METHODHANDLENATIVES_REGISTERNATIVES;
import static jbse.bc.Signatures.JAVA_METHODHANDLENATIVES_RESOLVE;
import static jbse.bc.Signatures.JAVA_METHODTYPEFORM;
import static jbse.bc.Signatures.JAVA_OBJECT_CLONE;
import static jbse.bc.Signatures.JAVA_OBJECT_GETCLASS;
import static jbse.bc.Signatures.JAVA_OBJECT_HASHCODE;
import static jbse.bc.Signatures.JAVA_OBJECT_NOTIFYALL;
import static jbse.bc.Signatures.JAVA_OBJECT_REGISTERNATIVES;
import static jbse.bc.Signatures.JAVA_REFLECT_ARRAY_NEWARRAY;
import static jbse.bc.Signatures.JAVA_RUNTIME_AVAILABLEPROCESSORS;
import static jbse.bc.Signatures.JAVA_SHORT_SHORTCACHE;
import static jbse.bc.Signatures.JAVA_STANDARDCHARSETS;
import static jbse.bc.Signatures.JAVA_STRICTMATH_ACOS;
import static jbse.bc.Signatures.JAVA_STRICTMATH_ASIN;
import static jbse.bc.Signatures.JAVA_STRICTMATH_ATAN;
import static jbse.bc.Signatures.JAVA_STRICTMATH_ATAN2;
import static jbse.bc.Signatures.JAVA_STRICTMATH_CBRT;
import static jbse.bc.Signatures.JAVA_STRICTMATH_COS;
import static jbse.bc.Signatures.JAVA_STRICTMATH_COSH;
import static jbse.bc.Signatures.JAVA_STRICTMATH_EXP;
import static jbse.bc.Signatures.JAVA_STRICTMATH_EXPM1;
import static jbse.bc.Signatures.JAVA_STRICTMATH_HYPOT;
import static jbse.bc.Signatures.JAVA_STRICTMATH_IEEEREMAINDER;
import static jbse.bc.Signatures.JAVA_STRICTMATH_LOG;
import static jbse.bc.Signatures.JAVA_STRICTMATH_LOG10;
import static jbse.bc.Signatures.JAVA_STRICTMATH_LOG1P;
import static jbse.bc.Signatures.JAVA_STRICTMATH_POW;
import static jbse.bc.Signatures.JAVA_STRICTMATH_SIN;
import static jbse.bc.Signatures.JAVA_STRICTMATH_SINH;
import static jbse.bc.Signatures.JAVA_STRICTMATH_SQRT;
import static jbse.bc.Signatures.JAVA_STRICTMATH_TAN;
import static jbse.bc.Signatures.JAVA_STRICTMATH_TANH;
import static jbse.bc.Signatures.JAVA_STRING_HASHCODE;
import static jbse.bc.Signatures.JAVA_STRING_INTERN;
import static jbse.bc.Signatures.JAVA_STRINGBUILDER_APPEND_BOOLEAN;
import static jbse.bc.Signatures.JAVA_STRINGBUILDER_APPEND_CHAR;
import static jbse.bc.Signatures.JAVA_STRINGBUILDER_APPEND_DOUBLE;
import static jbse.bc.Signatures.JAVA_STRINGBUILDER_APPEND_FLOAT;
import static jbse.bc.Signatures.JAVA_STRINGBUILDER_APPEND_INT;
import static jbse.bc.Signatures.JAVA_STRINGBUILDER_APPEND_LONG;
import static jbse.bc.Signatures.JAVA_SYSTEM_ARRAYCOPY;
import static jbse.bc.Signatures.JAVA_SYSTEM_CURRENTTIMEMILLIS;
import static jbse.bc.Signatures.JAVA_SYSTEM_IDENTITYHASHCODE;
import static jbse.bc.Signatures.JAVA_SYSTEM_INITPROPERTIES;
import static jbse.bc.Signatures.JAVA_SYSTEM_MAPLIBRARYNAME;
import static jbse.bc.Signatures.JAVA_SYSTEM_NANOTIME;
import static jbse.bc.Signatures.JAVA_SYSTEM_REGISTERNATIVES;
import static jbse.bc.Signatures.JAVA_SYSTEM_SETERR0;
import static jbse.bc.Signatures.JAVA_SYSTEM_SETIN0;
import static jbse.bc.Signatures.JAVA_SYSTEM_SETOUT0;
import static jbse.bc.Signatures.JAVA_THREAD_CURRENTTHREAD;
import static jbse.bc.Signatures.JAVA_THREAD_ISALIVE;
import static jbse.bc.Signatures.JAVA_THREAD_REGISTERNATIVES;
import static jbse.bc.Signatures.JAVA_THREAD_SETPRIORITY0;
import static jbse.bc.Signatures.JAVA_THREAD_START0;
import static jbse.bc.Signatures.JAVA_THROWABLE_FILLINSTACKTRACE;
import static jbse.bc.Signatures.JAVA_THROWABLE_GETSTACKTRACEDEPTH;
import static jbse.bc.Signatures.JAVA_THROWABLE_GETSTACKTRACEELEMENT;
import static jbse.bc.Signatures.JAVA_THROWABLE_SENTINELHOLDER;
import static jbse.bc.Signatures.JAVA_TREESET;
import static jbse.bc.Signatures.JAVA_UNIXFILESYSTEM_CANONICALIZE0;
import static jbse.bc.Signatures.JAVA_UNIXFILESYSTEM_GETBOOLEANATTRIBUTES0;
import static jbse.bc.Signatures.JAVA_UNIXFILESYSTEM_GETLASTMODIFIEDTIME;
import static jbse.bc.Signatures.JAVA_UNIXFILESYSTEM_INITIDS;
import static jbse.bc.Signatures.JAVA_UNIXFILESYSTEM_LIST;
import static jbse.bc.Signatures.JAVA_VOID;
import static jbse.bc.Signatures.JAVA_WINNTFILESYSTEM_CANONICALIZE0;
import static jbse.bc.Signatures.JAVA_WINNTFILESYSTEM_GETBOOLEANATTRIBUTES;
import static jbse.bc.Signatures.JAVA_WINNTFILESYSTEM_GETLASTMODIFIEDTIME;
import static jbse.bc.Signatures.JAVA_WINNTFILESYSTEM_INITIDS;
import static jbse.bc.Signatures.JAVA_WINNTFILESYSTEM_LIST;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ANY;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ASSUMECLASSNOTINITIALIZED;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ENDGUIDANCE;
import static jbse.bc.Signatures.JBSE_ANALYSIS_FAIL;
import static jbse.bc.Signatures.JBSE_ANALYSIS_IGNORE;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISRESOLVED;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISRUNBYJBSE;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISSYMBOLIC_BOOLEAN;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISSYMBOLIC_BYTE;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISSYMBOLIC_CHAR;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISSYMBOLIC_DOUBLE;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISSYMBOLIC_FLOAT;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISSYMBOLIC_INT;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISSYMBOLIC_LONG;
import static jbse.bc.Signatures.JBSE_ANALYSIS_ISSYMBOLIC_SHORT;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SUCCEED;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SYMBOLNAME_BOOLEAN;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SYMBOLNAME_BYTE;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SYMBOLNAME_CHAR;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SYMBOLNAME_DOUBLE;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SYMBOLNAME_FLOAT;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SYMBOLNAME_INT;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SYMBOLNAME_LONG;
import static jbse.bc.Signatures.JBSE_ANALYSIS_SYMBOLNAME_SHORT;
import static jbse.bc.Signatures.SUN_NATIVECONSTRUCTORACCESSORIMPL_NEWINSTANCE0;
import static jbse.bc.Signatures.SUN_REFLECTION_GETCALLERCLASS;
import static jbse.bc.Signatures.SUN_REFLECTION_GETCLASSACCESSFLAGS;
import static jbse.bc.Signatures.SUN_SIGNAL_FINDSIGNAL;
import static jbse.bc.Signatures.SUN_SIGNAL_HANDLE0;
import static jbse.bc.Signatures.SUN_UNSAFE_ADDRESSSIZE;
import static jbse.bc.Signatures.SUN_UNSAFE_ALLOCATEMEMORY;
import static jbse.bc.Signatures.SUN_UNSAFE_ARRAYBASEOFFSET;
import static jbse.bc.Signatures.SUN_UNSAFE_ARRAYINDEXSCALE;
import static jbse.bc.Signatures.SUN_UNSAFE_COMPAREANDSWAPINT;
import static jbse.bc.Signatures.SUN_UNSAFE_COMPAREANDSWAPLONG;
import static jbse.bc.Signatures.SUN_UNSAFE_COMPAREANDSWAPOBJECT;
import static jbse.bc.Signatures.SUN_UNSAFE_DEFINEANONYMOUSCLASS;
import static jbse.bc.Signatures.SUN_UNSAFE_ENSURECLASSINITIALIZED;
import static jbse.bc.Signatures.SUN_UNSAFE_FREEMEMORY;
import static jbse.bc.Signatures.SUN_UNSAFE_GETBYTE;
import static jbse.bc.Signatures.SUN_UNSAFE_GETINTVOLATILE;
import static jbse.bc.Signatures.SUN_UNSAFE_GETLONG;
import static jbse.bc.Signatures.SUN_UNSAFE_GETOBJECTVOLATILE;
import static jbse.bc.Signatures.SUN_UNSAFE_OBJECTFIELDOFFSET;
import static jbse.bc.Signatures.SUN_UNSAFE_PUTLONG;
import static jbse.bc.Signatures.SUN_UNSAFE_PUTOBJECTVOLATILE;
import static jbse.bc.Signatures.SUN_UNSAFE_REGISTERNATIVES;
import static jbse.bc.Signatures.SUN_UNSAFE_SHOULDBEINITIALIZED;
import static jbse.bc.Signatures.SUN_URLCLASSPATH_GETLOOKUPCACHEURLS;
import static jbse.bc.Signatures.SUN_VERIFYACCESS;
import static jbse.bc.Signatures.SUN_VM_INITIALIZE;
import static jbse.bc.Signatures.SUN_WRAPPER_FORMAT;
import static jbse.bc.Signatures.noclass_REGISTERLOADEDCLASS;
import static jbse.bc.Signatures.noclass_REGISTERMETHODTYPE;
import static jbse.bc.Signatures.noclass_SETPHASEPOSTINIT;
import static jbse.bc.Signatures.noclass_SETSTANDARDCLASSLOADERSREADY;
import static jbse.bc.Signatures.noclass_STORELINKEDMETHODANDAPPENDIX;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jbse.algo.exc.MetaUnsupportedException;
import jbse.bc.ClassFile;
import jbse.bc.ClassFileFactory;
import jbse.bc.ClassHierarchy;
import jbse.bc.Classpath;
import jbse.bc.Signature;
import jbse.common.exc.UnexpectedInternalException;
import jbse.dec.DecisionProcedureAlgorithms;
import jbse.mem.State;
import jbse.rules.TriggerRulesRepo;
import jbse.tree.DecisionAlternative;
import jbse.tree.DecisionAlternativeComparators;
import jbse.tree.StateTree;
import jbse.tree.StateTree.BreadthMode;
import jbse.tree.StateTree.StateIdentificationMode;
import jbse.val.Calculator;
import jbse.val.ReferenceConcrete;

/**
 * Class containing an execution context, i.e., everything 
 * different from the symbolic execution state necessary to 
 * perform an execution step.
 * 
 * @author Pietro Braione
 */
public final class ExecutionContext {
    /** The maximum length of an array to be granted simple representation. Used during initialization. */
    public final int maxSimpleArrayLength;
    
    /** The maximum heap size expressed as maximum number of objects. Used during initialization. */
    public final long maxHeapSize;
    
    /** The {@link Classpath}. Used during initialization. */
    public final Classpath classpath;

    /** The {@link Signature} of the root (initial) method. Used during initialization. */
    public final Signature rootMethodSignature;

    /** The {@link Calculator}. Used during initialization. */
    public final Calculator calc;

    /** The class for the symbolic execution's {@link ClassFileFactory} 
     * (injected dependency). Used during initialization.
     */
    public final Class<? extends ClassFileFactory> classFileFactoryClass;

    /** 
     * Maps class names to the names of the subclasses that may be 
     * used to expand references. Used during initialization.
     */
    public final Map<String, Set<String>> expansionBackdoor;

    /** 
     * The initial {@link State} of symbolic execution. It is a prototype 
     * that will be cloned by its getter. 
     */
    private State initialState;

    /** The symbolic execution's {@link DecisionAlternativeComparators}. */
    private final DecisionAlternativeComparators comparators;

    /** The {@link DispatcherBytecodeAlgorithm}. */
    public final DispatcherBytecodeAlgorithm dispatcher = new DispatcherBytecodeAlgorithm();

    /** 
     * The {@link DispatcherMeta} for handling methods with 
     * meta-level implementation. 
     */
    public final DispatcherMeta dispatcherMeta = new DispatcherMeta();

    /** Maps method signatures to their base-level overrides. */
    public final HashMap<Signature, Signature> baseOverrides = new HashMap<>();

    /** The symbolic execution's {@link DecisionProcedureAlgorithms}. */
    public final DecisionProcedureAlgorithms decisionProcedure;

    /** The symbolic execution's {@link StateTree}. */
    public final StateTree stateTree;

    /** 
     * The {@link TriggerManager} that handles reference resolution events
     * and executes triggers. 
     */
    public final TriggerManager triggerManager;
    
    /** A {@link ReferenceConcrete} to the main thread group created at init time. */
    private ReferenceConcrete mainThreadGroup;
    
    /** A {@link ReferenceConcrete} to the main thread created at init time. */
    private ReferenceConcrete mainThread;

    /**
     * Constructor.
     * 
     * @param initialState the initial {@code State}, or {@code null} if no
     *        initial state. Warning: all the remaining parameters
     *        must be coherent with it, if not {@code null} (e.g., 
     *        {@code calc} must be the calculator used to create  
     *        {@code initialState}). It will not be modified, but
     *        it shall not be modified externally.
     * @param maxSimpleArrayLength the maximum length an array may have
     *        to be granted simple representation.
     * @param maxHeapSize a {@code long}, the maximum size of the
     *        heap expressed as maximum number of objects it can store.
     * @param classpath a {@link Classpath} object, containing 
     *        information about the classpath of the symbolic execution.
     * @param rootMethodSignature the {@link Signature} of the root method
     *        of the symbolic execution.
     * @param calc a {@link Calculator}.
     * @param decisionProcedure a {@link DecisionProcedureAlgorithms}.
     * @param stateIdentificationMode a {@link StateIdentificationMode}.
     * @param breadthMode a {@link BreadthMode}.
     * @param classFileFactoryClass a {@link Class}{@code <? extends }{@link ClassFileFactory}{@code >}
     *        that will be instantiated by the engine to retrieve classfiles. It must 
     *        provide a parameterless public constructor.
     * @param expansionBackdoor a 
     *        {@link Map}{@code <}{@link String}{@code , }{@link Set}{@code <}{@link String}{@code >>}
     *        associating class names to sets of names of their subclasses. It 
     *        is used in place of the class hierarchy to perform reference expansion.
     * @param rulesTrigger a {@link TriggerRulesRepo}.
     * @param comparators a {@link DecisionAlternativeComparators} which
     *        will be used to establish the order of exploration
     *        for sibling branches.
     * @param nativeInvoker a {@link NativeInvoker} which will be used
     *        to execute native methods.
     */
    public ExecutionContext(State initialState,
                            int maxSimpleArrayLength,
                            long maxHeapSize,
                            Classpath classpath,
                            Signature rootMethodSignature,
                            Calculator calc, 
                            DecisionProcedureAlgorithms decisionProcedure,
                            StateIdentificationMode stateIdentificationMode,
                            BreadthMode breadthMode,
                            Class<? extends ClassFileFactory> classFileFactoryClass, 
                            Map<String, Set<String>> expansionBackdoor,
                            TriggerRulesRepo rulesTrigger,
                            DecisionAlternativeComparators comparators) {
        this.initialState = initialState;
        this.maxSimpleArrayLength = maxSimpleArrayLength;
        this.maxHeapSize = maxHeapSize;
        this.classpath = classpath;
        this.rootMethodSignature = rootMethodSignature;
        this.calc = calc;
        this.decisionProcedure = decisionProcedure;
        this.stateTree = new StateTree(stateIdentificationMode, breadthMode);
        this.classFileFactoryClass = classFileFactoryClass;
        this.expansionBackdoor = new HashMap<>(expansionBackdoor);      //safety copy
        this.triggerManager = new TriggerManager(rulesTrigger.clone()); //safety copy
        this.comparators = comparators;

        //defaults
        try {
            //JRE methods
            addBaseOverridden(JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION_1,     BASE_JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION_1);
            addBaseOverridden(JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION_2,     BASE_JAVA_ACCESSCONTROLLER_DOPRIVILEGED_EXCEPTION_2);
            addBaseOverridden(JAVA_ACCESSCONTROLLER_DOPRIVILEGED_NOEXCEPTION,     BASE_JAVA_ACCESSCONTROLLER_DOPRIVILEGED_NOEXCEPTION);
            addBaseOverridden(JAVA_ACCESSCONTROLLER_GETSTACKACCESSCONTROLCONTEXT, BASE_JAVA_ACCESSCONTROLLER_GETSTACKACCESSCONTROLCONTEXT);
            addBaseOverridden(JAVA_ATOMICLONG_VMSUPPORTSCS8,                      BASE_JAVA_ATOMICLONG_VMSUPPORTSCS8);
            addBaseOverridden(JAVA_CLASS_DESIREDASSERTIONSTATUS0,                 BASE_JAVA_CLASS_DESIREDASSERTIONSTATUS0);
            addMetaOverridden(JAVA_CLASS_FORNAME0,                                ALGO_JAVA_CLASS_FORNAME0);
            addMetaOverridden(JAVA_CLASS_GETCOMPONENTTYPE,                        ALGO_JAVA_CLASS_GETCOMPONENTTYPE);
            addMetaOverridden(JAVA_CLASS_GETDECLAREDCONSTRUCTORS0,                ALGO_JAVA_CLASS_GETDECLAREDCONSTRUCTORS0);
            addMetaOverridden(JAVA_CLASS_GETDECLAREDFIELDS0,                      ALGO_JAVA_CLASS_GETDECLAREDFIELDS0);
            addMetaOverridden(JAVA_CLASS_GETDECLARINGCLASS0,                      ALGO_JAVA_CLASS_GETDECLARINGCLASS0);
            addMetaOverridden(JAVA_CLASS_GETENCLOSINGMETHOD0,                     ALGO_JAVA_CLASS_GETENCLOSINGMETHOD0);
            addMetaOverridden(JAVA_CLASS_GETMODIFIERS,                            ALGO_JAVA_CLASS_GETMODIFIERS);
            addMetaOverridden(JAVA_CLASS_GETNAME0,                                ALGO_JAVA_CLASS_GETNAME0);
            addMetaOverridden(JAVA_CLASS_GETPRIMITIVECLASS,                       ALGO_JAVA_CLASS_GETPRIMITIVECLASS);
            addMetaOverridden(JAVA_CLASS_GETSUPERCLASS,                           ALGO_JAVA_CLASS_GETSUPERCLASS);
            addMetaOverridden(JAVA_CLASS_ISARRAY,                                 ALGO_JAVA_CLASS_ISARRAY);
            addMetaOverridden(JAVA_CLASS_ISASSIGNABLEFROM,                        ALGO_JAVA_CLASS_ISASSIGNABLEFROM);
            addMetaOverridden(JAVA_CLASS_ISINSTANCE,                              ALGO_JAVA_CLASS_ISINSTANCE);
            addMetaOverridden(JAVA_CLASS_ISINTERFACE,                             ALGO_JAVA_CLASS_ISINTERFACE);
            addMetaOverridden(JAVA_CLASS_ISPRIMITIVE,                             ALGO_JAVA_CLASS_ISPRIMITIVE);
            addMetaOverridden(JAVA_CLASS_REGISTERNATIVES,                         ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_CLASSLOADER_FINDBOOTSTRAPCLASS,                ALGO_JAVA_CLASSLOADER_FINDBOOTSTRAPCLASS);
            addBaseOverridden(JAVA_CLASSLOADER_FINDBUILTINLIB,                    BASE_JAVA_CLASSLOADER_FINDBUILTINLIB);
            addMetaOverridden(JAVA_CLASSLOADER_FINDLOADEDCLASS0,                  ALGO_JAVA_CLASSLOADER_FINDLOADEDCLASS0);
            addMetaOverridden(JAVA_CLASSLOADER_NATIVELIBRARY_LOAD,                ALGO_JAVA_CLASSLOADER_NATIVELIBRARY_LOAD);
            addMetaOverridden(JAVA_CLASSLOADER_REGISTERNATIVES,                   ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_DOUBLE_DOUBLETORAWLONGBITS,                    ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_DOUBLE_LONGBITSTODOUBLE,                       ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_FILEDESCRIPTOR_INITIDS,                        ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_FILEINPUTSTREAM_AVAILABLE,                     ALGO_JAVA_FILEINPUTSTREAM_AVAILABLE);
            addMetaOverridden(JAVA_FILEINPUTSTREAM_CLOSE0,                        ALGO_JAVA_FILEINPUTSTREAM_CLOSE0);
            addMetaOverridden(JAVA_FILEINPUTSTREAM_INITIDS,                       ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_FILEINPUTSTREAM_OPEN0,                         ALGO_JAVA_FILEINPUTSTREAM_OPEN0);
            addMetaOverridden(JAVA_FILEINPUTSTREAM_READBYTES,                     ALGO_JAVA_FILEINPUTSTREAM_READBYTES);
            addMetaOverridden(JAVA_FILEOUTPUTSTREAM_INITIDS,                      ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_FLOAT_FLOATTORAWINTBITS,                       ALGO_INVOKEMETA_METACIRCULAR);
            addBaseOverridden(JAVA_METHODHANDLENATIVES_GETCONSTANT,               BASE_JAVA_METHODHANDLENATIVES_GETCONSTANT);
            addMetaOverridden(JAVA_METHODHANDLENATIVES_REGISTERNATIVES,           ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_METHODHANDLENATIVES_RESOLVE,                   ALGO_JAVA_METHODHANDLENATIVES_RESOLVE);
            addMetaOverridden(JAVA_OBJECT_CLONE,                                  ALGO_JAVA_OBJECT_CLONE);
            addMetaOverridden(JAVA_OBJECT_GETCLASS,                               ALGO_JAVA_OBJECT_GETCLASS);
            addMetaOverridden(JAVA_OBJECT_HASHCODE,                               ALGO_JAVA_OBJECT_HASHCODE);
            addMetaOverridden(JAVA_OBJECT_NOTIFYALL,                              ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_OBJECT_REGISTERNATIVES,                        ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_REFLECT_ARRAY_NEWARRAY,                        ALGO_JAVA_REFLECT_ARRAY_NEWARRAY);
            addBaseOverridden(JAVA_RUNTIME_AVAILABLEPROCESSORS,                   BASE_JAVA_RUNTIME_AVAILABLEPROCESSORS);
            addMetaOverridden(JAVA_STRICTMATH_ACOS,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_ASIN,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_ATAN,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_ATAN2,                              ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_CBRT,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_COS,                                ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_COSH,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_EXP,                                ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_EXPM1,                              ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_HYPOT,                              ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_IEEEREMAINDER,                      ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_LOG,                                ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_LOG10,                              ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_LOG1P,                              ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_POW,                                ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_SIN,                                ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_SINH,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_SQRT,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_TAN,                                ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRICTMATH_TANH,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_STRING_HASHCODE,                               ALGO_JAVA_STRING_HASHCODE);
            addMetaOverridden(JAVA_STRING_INTERN,                                 ALGO_JAVA_STRING_INTERN);
            addMetaOverridden(JAVA_STRINGBUILDER_APPEND_BOOLEAN,                  ALGO_JAVA_STRINGBUILDER_APPEND);
            addMetaOverridden(JAVA_STRINGBUILDER_APPEND_CHAR,                     ALGO_JAVA_STRINGBUILDER_APPEND);
            addMetaOverridden(JAVA_STRINGBUILDER_APPEND_DOUBLE,                   ALGO_JAVA_STRINGBUILDER_APPEND);
            addMetaOverridden(JAVA_STRINGBUILDER_APPEND_FLOAT,                    ALGO_JAVA_STRINGBUILDER_APPEND);
            addMetaOverridden(JAVA_STRINGBUILDER_APPEND_INT,                      ALGO_JAVA_STRINGBUILDER_APPEND);
            addMetaOverridden(JAVA_STRINGBUILDER_APPEND_LONG,                     ALGO_JAVA_STRINGBUILDER_APPEND);
            addMetaOverridden(JAVA_SYSTEM_ARRAYCOPY,                              ALGO_JAVA_SYSTEM_ARRAYCOPY);
            addMetaOverridden(JAVA_SYSTEM_CURRENTTIMEMILLIS,                      ALGO_INVOKEMETA_METACIRCULAR);
            addBaseOverridden(JAVA_SYSTEM_INITPROPERTIES,                         BASE_JAVA_SYSTEM_INITPROPERTIES);
            addMetaOverridden(JAVA_SYSTEM_IDENTITYHASHCODE,                       ALGO_JAVA_SYSTEM_IDENTITYHASHCODE);
            addMetaOverridden(JAVA_SYSTEM_MAPLIBRARYNAME,                         ALGO_JAVA_SYSTEM_MAPLIBRARYNAME);
            addMetaOverridden(JAVA_SYSTEM_NANOTIME,                               ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_SYSTEM_REGISTERNATIVES,                        ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_SYSTEM_SETERR0,                                ALGO_JAVA_SYSTEM_SETERR0);
            addMetaOverridden(JAVA_SYSTEM_SETIN0,                                 ALGO_JAVA_SYSTEM_SETIN0);
            addMetaOverridden(JAVA_SYSTEM_SETOUT0,                                ALGO_JAVA_SYSTEM_SETOUT0);
            addMetaOverridden(JAVA_THREAD_CURRENTTHREAD,                          ALGO_JAVA_THREAD_CURRENTTHREAD);
            addBaseOverridden(JAVA_THREAD_ISALIVE,                                BASE_JAVA_THREAD_ISALIVE);
            addMetaOverridden(JAVA_THREAD_REGISTERNATIVES,                        ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_THREAD_SETPRIORITY0,                           ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_THREAD_START0,                                 ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_THROWABLE_FILLINSTACKTRACE,                    ALGO_JAVA_THROWABLE_FILLINSTACKTRACE);
            addMetaOverridden(JAVA_THROWABLE_GETSTACKTRACEDEPTH,                  ALGO_JAVA_THROWABLE_GETSTACKTRACEDEPTH);
            addMetaOverridden(JAVA_THROWABLE_GETSTACKTRACEELEMENT,                ALGO_JAVA_THROWABLE_GETSTACKTRACEELEMENT);
            addMetaOverridden(JAVA_UNIXFILESYSTEM_CANONICALIZE0,                  ALGO_JAVA_XFILESYSTEM_CANONICALIZE0);
            addMetaOverridden(JAVA_UNIXFILESYSTEM_GETBOOLEANATTRIBUTES0,          ALGO_JAVA_XFILESYSTEM_GETBOOLEANATTRIBUTESX);
            addMetaOverridden(JAVA_UNIXFILESYSTEM_GETLASTMODIFIEDTIME,            ALGO_JAVA_XFILESYSTEM_GETLASTMODIFIEDTIME);
            addMetaOverridden(JAVA_UNIXFILESYSTEM_INITIDS,                        ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_UNIXFILESYSTEM_LIST,                           ALGO_JAVA_XFILESYSTEM_LIST);
            addMetaOverridden(JAVA_WINNTFILESYSTEM_CANONICALIZE0,                 ALGO_JAVA_XFILESYSTEM_CANONICALIZE0);
            addMetaOverridden(JAVA_WINNTFILESYSTEM_GETBOOLEANATTRIBUTES,          ALGO_JAVA_XFILESYSTEM_GETBOOLEANATTRIBUTESX);
            addMetaOverridden(JAVA_WINNTFILESYSTEM_GETLASTMODIFIEDTIME,           ALGO_JAVA_XFILESYSTEM_GETLASTMODIFIEDTIME);
            addMetaOverridden(JAVA_WINNTFILESYSTEM_INITIDS,                       ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(JAVA_WINNTFILESYSTEM_LIST,                          ALGO_JAVA_XFILESYSTEM_LIST);
            addMetaOverridden(SUN_NATIVECONSTRUCTORACCESSORIMPL_NEWINSTANCE0,     ALGO_SUN_NATIVECONSTRUCTORACCESSORIMPL_NEWINSTANCE0);
            addMetaOverridden(SUN_REFLECTION_GETCALLERCLASS,                      ALGO_SUN_REFLECTION_GETCALLERCLASS);
            addMetaOverridden(SUN_REFLECTION_GETCLASSACCESSFLAGS,                 ALGO_SUN_REFLECTION_GETCLASSACCESSFLAGS);
            addBaseOverridden(SUN_SIGNAL_FINDSIGNAL,                              BASE_SUN_SIGNAL_FINDSIGNAL);
            addBaseOverridden(SUN_SIGNAL_HANDLE0,                                 BASE_SUN_SIGNAL_HANDLE0);
            addBaseOverridden(SUN_UNSAFE_ADDRESSSIZE,                             BASE_SUN_UNSAFE_ADDRESSSIZE);
            addMetaOverridden(SUN_UNSAFE_ALLOCATEMEMORY,                          ALGO_SUN_UNSAFE_ALLOCATEMEMORY);
            addBaseOverridden(SUN_UNSAFE_ARRAYBASEOFFSET,                         BASE_SUN_UNSAFE_ARRAYBASEOFFSET);
            addBaseOverridden(SUN_UNSAFE_ARRAYINDEXSCALE,                         BASE_SUN_UNSAFE_ARRAYINDEXSCALE);
            addMetaOverridden(SUN_UNSAFE_COMPAREANDSWAPINT,                       ALGO_SUN_UNSAFE_COMPAREANDSWAPINT);
            addMetaOverridden(SUN_UNSAFE_COMPAREANDSWAPLONG,                      ALGO_SUN_UNSAFE_COMPAREANDSWAPLONG);
            addMetaOverridden(SUN_UNSAFE_COMPAREANDSWAPOBJECT,                    ALGO_SUN_UNSAFE_COMPAREANDSWAPOBJECT);
            addMetaOverridden(SUN_UNSAFE_DEFINEANONYMOUSCLASS,                    ALGO_SUN_UNSAFE_DEFINEANONYMOUSCLASS);
            addMetaOverridden(SUN_UNSAFE_ENSURECLASSINITIALIZED,                  ALGO_SUN_UNSAFE_ENSURECLASSINITIALIZED);
            addMetaOverridden(SUN_UNSAFE_FREEMEMORY,                              ALGO_SUN_UNSAFE_FREEMEMORY);
            addMetaOverridden(SUN_UNSAFE_GETBYTE,                                 ALGO_SUN_UNSAFE_GETBYTE);
            addMetaOverridden(SUN_UNSAFE_GETINTVOLATILE,                          ALGO_SUN_UNSAFE_GETINTVOLATILE);
            addMetaOverridden(SUN_UNSAFE_GETLONG,                                 ALGO_SUN_UNSAFE_GETLONG);
            addMetaOverridden(SUN_UNSAFE_GETOBJECTVOLATILE,                       ALGO_SUN_UNSAFE_GETOBJECTVOLATILE);
            addMetaOverridden(SUN_UNSAFE_OBJECTFIELDOFFSET,                       ALGO_SUN_UNSAFE_OBJECTFIELDOFFSET);
            addMetaOverridden(SUN_UNSAFE_PUTLONG,                                 ALGO_SUN_UNSAFE_PUTLONG);
            addMetaOverridden(SUN_UNSAFE_PUTOBJECTVOLATILE,                       ALGO_SUN_UNSAFE_PUTOBJECTVOLATILE);
            addMetaOverridden(SUN_UNSAFE_REGISTERNATIVES,                         ALGO_INVOKEMETA_METACIRCULAR);
            addMetaOverridden(SUN_UNSAFE_SHOULDBEINITIALIZED,                     ALGO_SUN_UNSAFE_SHOULDBEINITIALIZED);
            addBaseOverridden(SUN_URLCLASSPATH_GETLOOKUPCACHEURLS,                BASE_SUN_URLCLASSPATH_GETLOOKUPCACHEURLS);
            addMetaOverridden(SUN_VM_INITIALIZE,                                  ALGO_INVOKEMETA_METACIRCULAR);

            //jbse.meta.Analysis methods
            addMetaOverridden(JBSE_ANALYSIS_ANY,                       ALGO_JBSE_ANALYSIS_ANY);
            addMetaOverridden(JBSE_ANALYSIS_ASSUMECLASSNOTINITIALIZED, ALGO_JBSE_ANALYSIS_ASSUMECLASSNOTINITIALIZED);
            addMetaOverridden(JBSE_ANALYSIS_ENDGUIDANCE,               ALGO_JBSE_ANALYSIS_ENDGUIDANCE);
            addMetaOverridden(JBSE_ANALYSIS_FAIL,                      ALGO_JBSE_ANALYSIS_FAIL);
            addMetaOverridden(JBSE_ANALYSIS_IGNORE,                    ALGO_JBSE_ANALYSIS_IGNORE);
            addMetaOverridden(JBSE_ANALYSIS_ISRESOLVED,                ALGO_JBSE_ANALYSIS_ISRESOLVED);
            addBaseOverridden(JBSE_ANALYSIS_ISRUNBYJBSE,               BASE_JBSE_ANALYSIS_ISRUNBYJBSE);
            addMetaOverridden(JBSE_ANALYSIS_ISSYMBOLIC_BOOLEAN,        ALGO_JBSE_ANALYSIS_ISSYMBOLIC);
            addMetaOverridden(JBSE_ANALYSIS_ISSYMBOLIC_BYTE,           ALGO_JBSE_ANALYSIS_ISSYMBOLIC);
            addMetaOverridden(JBSE_ANALYSIS_ISSYMBOLIC_CHAR,           ALGO_JBSE_ANALYSIS_ISSYMBOLIC);
            addMetaOverridden(JBSE_ANALYSIS_ISSYMBOLIC_DOUBLE,         ALGO_JBSE_ANALYSIS_ISSYMBOLIC);
            addMetaOverridden(JBSE_ANALYSIS_ISSYMBOLIC_FLOAT,          ALGO_JBSE_ANALYSIS_ISSYMBOLIC);
            addMetaOverridden(JBSE_ANALYSIS_ISSYMBOLIC_INT,            ALGO_JBSE_ANALYSIS_ISSYMBOLIC);
            addMetaOverridden(JBSE_ANALYSIS_ISSYMBOLIC_LONG,           ALGO_JBSE_ANALYSIS_ISSYMBOLIC);
            addMetaOverridden(JBSE_ANALYSIS_ISSYMBOLIC_SHORT,          ALGO_JBSE_ANALYSIS_ISSYMBOLIC);
            addMetaOverridden(JBSE_ANALYSIS_SUCCEED,                   ALGO_JBSE_ANALYSIS_SUCCEED);
            addMetaOverridden(JBSE_ANALYSIS_SYMBOLNAME_BOOLEAN,        ALGO_JBSE_ANALYSIS_SYMBOLNAME);
            addMetaOverridden(JBSE_ANALYSIS_SYMBOLNAME_BYTE,           ALGO_JBSE_ANALYSIS_SYMBOLNAME);
            addMetaOverridden(JBSE_ANALYSIS_SYMBOLNAME_CHAR,           ALGO_JBSE_ANALYSIS_SYMBOLNAME);
            addMetaOverridden(JBSE_ANALYSIS_SYMBOLNAME_DOUBLE,         ALGO_JBSE_ANALYSIS_SYMBOLNAME);
            addMetaOverridden(JBSE_ANALYSIS_SYMBOLNAME_FLOAT,          ALGO_JBSE_ANALYSIS_SYMBOLNAME);
            addMetaOverridden(JBSE_ANALYSIS_SYMBOLNAME_INT,            ALGO_JBSE_ANALYSIS_SYMBOLNAME);
            addMetaOverridden(JBSE_ANALYSIS_SYMBOLNAME_LONG,           ALGO_JBSE_ANALYSIS_SYMBOLNAME);
            addMetaOverridden(JBSE_ANALYSIS_SYMBOLNAME_SHORT,          ALGO_JBSE_ANALYSIS_SYMBOLNAME);
            
            //jbse classless (pseudo)methods
            addMetaOverridden(noclass_REGISTERLOADEDCLASS,          ALGO_noclass_REGISTERLOADEDCLASS);
            addMetaOverridden(noclass_REGISTERMETHODTYPE,           ALGO_noclass_REGISTERMETHODTYPE);
            addMetaOverridden(noclass_SETPHASEPOSTINIT,             ALGO_noclass_SETPHASEPOSTINIT);
            addMetaOverridden(noclass_SETSTANDARDCLASSLOADERSREADY, ALGO_noclass_SETSTANDARDCLASSLOADERSREADY);
            addMetaOverridden(noclass_STORELINKEDMETHODANDAPPENDIX, ALGO_noclass_STORELINKEDMETHODANDAPPENDIX);
        } catch (MetaUnsupportedException e) {
            throw new UnexpectedInternalException(e);
        }
    }

    /**
     * Sets the initial state. To be invoked whenever 
     * the engine parameters object provided through the 
     * constructor does not have an initial state.
     * 
     * @param initialState a {@link State}. The method
     *        stores in this execution contest a safety 
     *        copy of it.
     */
    public void setInitialState(State initialState) {
        this.initialState = initialState.clone();
    }

    /**
     * Returns the initial state.
     * 
     * @return a {@link State}, a clone of the initial state
     *         of the symbolic execution.
     */
    public State getInitialState() {
        return (this.initialState == null ? null : this.initialState.clone());
    }

    /**
     * Allows to customize the behavior of the invocations to a method 
     * by specifying another method that implements it.
     * 
     * @param methodSignature the {@link Signature} of a method.
     * @param delegateMethodSignature the {@link Signature} of another method
     *        that will be executed in place of the method with signature
     *        {@code methodSignature}.
     */
    public void addBaseOverridden(Signature methodSignature, Signature delegateMethodSignature) {
        this.baseOverrides.put(methodSignature, delegateMethodSignature);
    }

    /**
     * Determines whether a method has a base-level overriding implementation.
     * 
     * @param methodSignature the {@link Signature} of a method.
     * @return {@code true} iff an overriding base-level method for it was added
     *         by invoking {@link #addBaseOverridden(Signature, Signature)}.
     */
    public boolean isMethodBaseLevelOverridden(Signature methodSignature) {
        return this.baseOverrides.containsKey(methodSignature);
    }

    /**
     * Returns the signature of a base-level override implementation 
     * of a method. 
     * 
     * @param methodSignature the {@link Signature} of a method.
     * @return  the {@link Signature} of the method that overrides
     *          the one with signature {@code methodSignature} and
     *          that was previously set by invoking {@link #addBaseOverridden(Signature, Signature)}.
     */
    public Signature getBaseOverride(Signature methodSignature) {
        return this.baseOverrides.get(methodSignature);
    }

    /**
     * Allows to customize the behavior of the invocations to a method 
     * by specifying an {@link Algorithm} that implements its semantics.
     * 
     * @param methodSignature the {@link Signature} of a method. 
     * @param metaDelegateClassName a class name as a {@link String}, 
     *        indicating a class (that must be in the meta-level classpath, 
     *        must have a default constructor, must implement {@link Algorithm})
     *        of an algorithm that implements at the meta-level the 
     *        semantics of the invocations to the method with signature 
     *        {@code methodSignature}. 
     * @throws MetaUnsupportedException if the class indicated in 
     *         {@code metaDelegateClassName} does not exist, or cannot be loaded 
     *         or instantiated for any reason (misses from the meta-level classpath, 
     *         has insufficient visibility, does not extend {@link Algorithm}...).
     */
    public void addMetaOverridden(Signature methodSignature, String metaDelegateClassName) 
    throws MetaUnsupportedException {
        this.dispatcherMeta.loadAlgoMetaOverridden(methodSignature, metaDelegateClassName);
    }

    /**
     * Allows to customize the behavior of the invocations to a method 
     * by treating all the invocations of a given method as returning 
     * the application of an uninterpreted symbolic function
     * with no side effect.
     * 
     * @param methodSignature the {@link Signature} of a method. 
     * @param functionName the name of the uninterpreted symbolic function
     *        whose application to the invocation parameter is 
     *        the result of all the invocations to {@code className.methodName}.
     */
    public void addUninterpreted(Signature methodSignature, String functionName) { 
        this.dispatcherMeta.loadAlgoUninterpreted(methodSignature, functionName);
    }

    /**
     * Determines whether a class has a pure static initializer, where with
     * "pure" we mean that its effect is independent on when the initializer
     * is executed.
     * 
     * @param classHierarchy a {@link ClassHierarchy}.
     * @param classFile the {@link ClassFile} for a class.
     * @return {@code true} iff the class has a pure static initializer.
     */
    public boolean hasClassAPureInitializer(ClassHierarchy hier, ClassFile classFile) {
        final String className = classFile.getClassName();
        return (
        className.equals(JAVA_BYTE_BYTECACHE) ||
        className.equals(JAVA_CHARACTER_CHARACTERCACHE) ||
        className.equals(JAVA_CHARSET_EXTENDEDPROVIDERHOLDER) ||
        className.equals(JAVA_IDENTITYHASHMAP) || 
        className.equals(JAVA_INVOKERBYTECODEGENERATOR_2) ||
        className.equals(JAVA_LAMBDAFORM) || 
        className.equals(JAVA_LAMBDAFORM_NAME) || 
        className.equals(JAVA_LINKEDLIST) || 
        className.equals(JAVA_LINKEDLIST_ENTRY) ||
        className.equals(JAVA_METHODTYPEFORM) || 
        className.equals(JAVA_SHORT_SHORTCACHE) || 
        className.equals(JAVA_STANDARDCHARSETS) || 
        className.equals(JAVA_TREESET) ||
        className.equals(JAVA_THROWABLE_SENTINELHOLDER) ||
        className.equals(JAVA_VOID) || 
        className.equals(SUN_VERIFYACCESS) ||
        className.equals(SUN_WRAPPER_FORMAT) ||
        hier.isSubclass(classFile, hier.getClassFileClassArray(CLASSLOADER_BOOT, JAVA_ENUM)));
    }
    
    /* TODO possibly grant initialization to these unpure classes (old code):
     *  className.equals(JAVA_BOUNDMETHODHANDLE) || //necessary for method handles
        className.equals(JAVA_BOUNDMETHODHANDLE_SPECIESDATA) || //necessary for method handles
        className.equals(JAVA_BOUNDMETHODHANDLE_SPECIES_L) || //necessary for method handles
        className.equals(JAVA_DIRECTMETHODHANDLE) || //wouldn't manage method handles otherwise
        className.equals(JAVA_INVOKERBYTECODEGENERATOR) || //the only nonfinal static field STATICALLY_INVOCABLE_PACKAGES is never modified
        className.equals(JAVA_METHODHANDLES) || //not really, but can be considered as it were (all final except ZERO_MHS and IDENTITY_MHS that are caches) 
        className.equals(JAVA_METHODHANDLES_LOOKUP) || //not really, but can be considered as it were (all final including PUBLIC_LOOKUP and IMPL_LOOKUP that are instances of Lookup - that is immutable - and except LOOKASIDE_TABLE, that seems to be a sort of cache) 
        className.equals(JAVA_METHODTYPE) || //not really, but can be considered as it were (all final except internTable and objectOnlyTypes that are caches) 
        className.equals(JAVA_SIMPLEMETHODHANDLE) || //necessary for method handles
        className.equals(SUN_LAUNCHERHELPER) || //necessary to JVM bootstrap (is it really?)
     */

    public <R extends DecisionAlternative> 
    SortedSet<R> mkDecisionResultSet(Class<R> superclassDecisionAlternatives) {
        final Comparator<R> comparator = this.comparators.get(superclassDecisionAlternatives);
        final TreeSet<R> retVal = new TreeSet<>(comparator);
        return retVal;
    }
    
    public void setMainThreadGroup(ReferenceConcrete mainThreadGroup) {
        this.mainThreadGroup = mainThreadGroup;
    }
    
    public ReferenceConcrete getMainThreadGroup() {
        return this.mainThreadGroup;
    }
    
    public void setMainThread(ReferenceConcrete mainThread) {
        this.mainThread = mainThread;
    }
    
    public ReferenceConcrete getMainThread() {
        return this.mainThread;
    }
}
