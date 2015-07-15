/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classObjects;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

/**
 *
 * @author
 * fvega
 */
public class AttributeTypeFactory {
    public static ClassAttribute getInstance(FieldNode field) {
        boolean isComplexType = false;
        
        if(field.signature != null)
            isComplexType = true;
        
        if((field.access & Opcodes.ACC_PRIVATE) != 0)
            return new PrivateClassAttribute(isComplexType);
        else if((field.access & Opcodes.ACC_PUBLIC) != 0)
            return new PublicClassAttribute(isComplexType);
        else
            return new ProtectedClassAttribute(isComplexType);
    }
}
