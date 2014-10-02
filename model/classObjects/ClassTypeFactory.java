/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classObjects;

import java.lang.reflect.Modifier;

/**
 *
 * @author
 * fvega
 */
public class ClassTypeFactory {
    public static ClassObject getInstance(Class rawClassObject) {
        if(Modifier.isAbstract(rawClassObject.getModifiers()))
            return new AbstractClassObject();
        else if(Modifier.isInterface(rawClassObject.getModifiers()))
            return new InterfaceClassObject();
        else
            return new ConcreteClassObject();
    }
}
