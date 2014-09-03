/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.classObjects;

import constants.ClassTypes;
import src.DiagramObject;

/**
 *
 * @author
 * fvega
 */
public class InterfaceObjectClass extends ClassObject {

    @Override
    public String draw() {
        return ClassTypes.INTERFACE + " " + this.name;
    }
    
}
