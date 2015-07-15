/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classObjects;

import model.diagram.DiagramObject;
import model.fileHandler.ComplexAttributeTypeParser;


/**
 *
 * @author
 * fvega
 */
public abstract class ClassAttribute implements DiagramObject {
    protected String name;
    protected String type;
    protected String complexType;
    protected boolean isComplexType;    
    
	public ClassAttribute() {
        this.isComplexType = false;
    }
	
	public ClassAttribute(boolean isComplexType) {
        this.isComplexType = isComplexType;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }        

    public String getComplexType() {
        return complexType;
    }

    public void setComplexType(String complexType) {
        this.complexType = complexType;
    }

    protected String drawCompleteAttributeName() {
        if(isComplexType) {
            return ComplexAttributeTypeParser.getComplexAttributeType(complexType) + type + ">";
        }
        else
            return type;
    }
}
