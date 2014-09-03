/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.classObjects;

import constants.ClassTypes;

/**
 *
 * @author
 * fvega
 */
public class AbstractClassObject extends ClassObject {

    @Override
    public String draw() {
        return ClassTypes.ABSTRACT + " " + this.name;
    }
    
}
