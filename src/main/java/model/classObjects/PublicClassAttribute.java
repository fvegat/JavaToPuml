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
public class PublicClassAttribute extends ClassAttribute {
    
	public PublicClassAttribute() {
        super();
    }
	
	public PublicClassAttribute(boolean isComplexType) {
        super(isComplexType);
    }
	
    @Override
    public String draw() {
        return ClassVisibilityTypes.PUBLIC + this.name + ":" + this.drawCompleteAttributeName();
    }
    
}
