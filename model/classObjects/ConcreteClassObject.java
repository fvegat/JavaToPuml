/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classObjects;

import constants.ClassTypes;

/**
 *
 * @author
 * fvega
 */
public class ConcreteClassObject extends ClassObject {

    @Override
    public String draw() {
        return ClassTypes.CONCRETE + " " + this.name;
    }
    
}
