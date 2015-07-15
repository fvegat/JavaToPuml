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
public class InterfaceClassObject extends ClassObject {

    @Override
    public String draw() {
        return ClassTypes.INTERFACE + " " + this.name;
    }
    
}
