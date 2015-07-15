/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classObjects;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

/**
 *
 * @author
 * fvega
 */
public class ClassTypeFactory {
    public static ClassObject getInstance(ClassNode classNode) {        
        if((classNode.access & Opcodes.ACC_INTERFACE) != 0)
            return new InterfaceClassObject();
        else if((classNode.access & Opcodes.ACC_ABSTRACT) != 0)
            return new AbstractClassObject();
        else
            return new ConcreteClassObject();
    }
}
