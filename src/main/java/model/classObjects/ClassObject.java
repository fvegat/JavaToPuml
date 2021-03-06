/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classObjects;

import java.util.List;
import model.diagram.DiagramObject;

/**
 *
 * @author
 * fvega
 */
public abstract class ClassObject implements DiagramObject {
    protected String name;
    protected List<DiagramObject> relations;
    protected List<DiagramObject> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DiagramObject> getRelations() {
        return relations;
    }

    public void setRelations(List<DiagramObject> relations) {
        this.relations = relations;
    }  

    public List<DiagramObject> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DiagramObject> attributes) {
        this.attributes = attributes;
    }
    
}
