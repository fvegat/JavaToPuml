/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classObjects;

import constants.ClassVisibilityTypes;

/**
 *
 * @author
 * fvega
 */
public class ProtectedClassAttribute extends ClassAttribute {
    
	public ProtectedClassAttribute() {
        super();
    }
	
	public ProtectedClassAttribute(boolean isComplexType) {
        super(isComplexType);
    }
	
	
    @Override
    public String draw() {
        return ClassVisibilityTypes.PROTECTED + this.name + ":" + this.drawCompleteAttributeName();
    }
    
}
