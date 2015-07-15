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
public class PrivateClassAttribute extends ClassAttribute {
	
	public PrivateClassAttribute() {
        super();
    }
	
	public PrivateClassAttribute(boolean isComplexType) {
        super(isComplexType);
    }
	
	@Override
    public String draw() {
        return ClassVisibilityTypes.PRIVATE + this.name + ":" + this.drawCompleteAttributeName();
    }
    
}
