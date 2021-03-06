package jbse.bc;

import static jbse.common.Type.REFERENCE;
import static jbse.common.Type.TYPEEND;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import jbse.bc.exc.AttributeNotFoundException;
import jbse.bc.exc.FieldNotFoundException;
import jbse.bc.exc.InvalidIndexException;
import jbse.bc.exc.MethodCodeNotFoundException;
import jbse.bc.exc.MethodNotFoundException;

/**
 * A {@link ClassFile} that can be created on-the-fly for code
 * snippets.
 * 
 * @author Pietro Braione
 */
public class ClassFileSnippet extends ClassFile {
    final HashMap<Integer, ConstantPoolValue> constants;
    final HashMap<Integer, Signature> signatures;
    final HashMap<Integer, String> classes;
    final int definingClassLoader;
    final String packageName;
    
    /**
     * Constructor.
     * 
     * @param snippet a {@link Snippet}.
     * @param definingClassLoader an {@code int}, the defining classloader 
     *        assumed for this {@link ClassFileSnippet}.
     * @param packageName a {@code String}, the name of the package where this
     *        {@link ClassFileSnippet} must be assumed to reside.
     */
    public ClassFileSnippet(Snippet snippet, int definingClassLoader, String packageName) {
        this.constants = new HashMap<>(snippet.getConstants());
        this.signatures = new HashMap<>(snippet.getSignatures());
        this.classes = new HashMap<>(snippet.getClasses());
        this.definingClassLoader = definingClassLoader;
        this.packageName = packageName;
    }
    
    @Override
    public byte[] getBinaryFileContent() {
        return null;
    }

    @Override
    public String getSourceFile() {
        return "";
    }

    @Override
    public int getMajorVersion() {
        return JAVA_8;
    }
    
    @Override
    public int getMinorVersion() {
        return 0;
    }
    
    @Override
    public String getClassName() {
        return packageName + "/";
    }
    
    @Override
    public String getInternalTypeName() {
        return "" + REFERENCE + getClassName() + TYPEEND;
    }
    
    @Override
    public int getDefiningClassLoader() {
        return this.definingClassLoader;
    }

    @Override
    public int getModifiers() {
        return getAccessFlags();
    }

    @Override
    public int getAccessFlags() {
        return Modifier.ABSTRACT | Modifier.FINAL | Modifier.PUBLIC;
    }
    
    @Override
    public boolean isDummy() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isEnum() {
        return false;
    }

    @Override
    public boolean isPrimitiveOrVoid() {
        return false;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isAbstract() {
        return true;
    }

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public boolean isProtected() {
        return false;
    }

    @Override
    public boolean isPackage() {
        return false;
    }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public boolean isSuperInvoke() {
        return true;
    }

    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }
    
    @Override
    public ClassFile getMemberClass() {
        return null;
    }
    
    @Override
    public boolean isAnonymousUnregistered() {
        return false;
    }
    
    @Override
    public ClassFile getHostClass() {
        return null;
    }

    @Override
    public String classContainer() {
        return null;
    }

    @Override
    public Signature getEnclosingMethodOrConstructor() {
        return null;
    }

    @Override
    public boolean isStatic() {
        return false;
    }
    
    @Override
    public int constantPoolSize() {
        return this.constants.size() + this.signatures.size() + this.classes.size();
    }

    @Override
    public boolean hasMethodImplementation(Signature methodSignature) {
        return false;
    }

    @Override
    public boolean hasMethodDeclaration(Signature methodSignature) {
        return false;
    }

    @Override
    public boolean hasOneSignaturePolymorphicMethodDeclaration(String methodName) {
        return false;
    }

    @Override
    public boolean isMethodAbstract(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean isMethodStatic(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean isMethodPublic(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean isMethodProtected(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean isMethodPackage(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean isMethodPrivate(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean isMethodNative(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean isMethodVarargs(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }
    
    @Override
    public boolean isMethodFinal(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean isMethodSignaturePolymorphic(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }
    
    @Override
    public boolean isMethodCallerSensitive(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public String getMethodGenericSignatureType(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public int getMethodModifiers(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public byte[] getMethodAnnotationsRaw(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public String[] getMethodAvailableAnnotations(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }
    
    @Override
    public String getMethodAnnotationParameterValueString(Signature methodSignature, String annotation, String parameter) {
        return null;
    }

    @Override
    public String[] getMethodThrownExceptions(Signature methodSignature) throws MethodNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public ExceptionTable getExceptionTable(Signature methodSignature)
    throws InvalidIndexException, MethodNotFoundException, MethodCodeNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public LocalVariableTable getLocalVariableTable(Signature methodSignature)
    throws MethodNotFoundException, MethodCodeNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public LineNumberTable getLineNumberTable(Signature methodSignature)
    throws MethodNotFoundException, MethodCodeNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public ConstantPoolValue getValueFromConstantPool(int index) throws InvalidIndexException {
        if (this.constants.containsKey(index)) {
            return this.constants.get(index);
        } else {
            throw new InvalidIndexException("index " + index + " not set");
        }
    }

    @Override
    public byte[] getMethodCodeBySignature(Signature methodSignature)
    throws MethodNotFoundException, MethodCodeNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public boolean hasFieldDeclaration(Signature fieldSignature) {
        return false;
    }

    @Override
    public boolean isFieldFinal(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public boolean isFieldPublic(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public boolean isFieldProtected(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public boolean isFieldPackage(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public boolean isFieldPrivate(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public boolean isFieldStatic(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public boolean hasFieldConstantValue(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public String getFieldGenericSignatureType(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public int getFieldModifiers(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public byte[] getFieldAnnotationsRaw(Signature fieldSignature) throws FieldNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }

    @Override
    public int fieldConstantValueIndex(Signature fieldSignature)
    throws FieldNotFoundException, AttributeNotFoundException {
        throw new FieldNotFoundException(fieldSignature.toString());
    }
    
    @Override
    public Signature[] getDeclaredFieldsNonStatic() {
        return new Signature[0];
    }

    @Override
    public Signature[] getDeclaredFieldsStatic() {
        return new Signature[0];
    }

    @Override
    public Signature[] getDeclaredFields() {
        return new Signature[0];
    }

    @Override
    public Signature getFieldSignature(int fieldRef) throws InvalidIndexException {
        if (this.signatures.containsKey(fieldRef)) {
            return this.signatures.get(fieldRef);
        } else {
            throw new InvalidIndexException("index " + fieldRef + " not set");
        }
    }

    @Override
    public Signature[] getDeclaredConstructors() {
        return new Signature[0];
    }

    @Override
    public Signature[] getDeclaredMethods() {
        return new Signature[0];
    }

    @Override
    public Signature getMethodSignature(int methodRef) throws InvalidIndexException {
        if (this.signatures.containsKey(methodRef)) {
            return this.signatures.get(methodRef);
        } else {
            throw new InvalidIndexException("index " + methodRef + " not set");
        }
    }

    @Override
    public Signature getInterfaceMethodSignature(int methodRef) throws InvalidIndexException {
        if (this.signatures.containsKey(methodRef)) {
            return this.signatures.get(methodRef);
        } else {
            throw new InvalidIndexException("index " + methodRef + " not set");
        }
    }

    @Override
    public String getClassSignature(int classRef) throws InvalidIndexException {
        if (this.classes.containsKey(classRef)) {
            return this.classes.get(classRef);
        } else {
            throw new InvalidIndexException("index " + classRef + " not set");
        }
    }
    
    @Override
    public ClassFile getSuperclass() {
        return null;
    }

    @Override
    public String getSuperclassName() {
        return null;
    }
    
    @Override
    public List<ClassFile> getSuperInterfaces() {
        return Collections.emptyList();
    }
    
    @Override
    public List<String> getSuperInterfaceNames() {
        return Collections.emptyList();
    }

    @Override
    public int getLocalVariableLength(Signature methodSignature)
    throws MethodNotFoundException, MethodCodeNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }

    @Override
    public int getCodeLength(Signature methodSignature) 
    throws MethodNotFoundException, MethodCodeNotFoundException {
        throw new MethodNotFoundException(methodSignature.toString());
    }
}
