/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.relationObjects;

import constants.ClassRelationTypes;

/**
 *
 * @author
 * fvega
 */
public class CompositionRelation extends ClassRelation {

    @Override
    public String draw() {
        return ClassRelationTypes.COMPOSITION + " " + this.relation;
    }
    
}
