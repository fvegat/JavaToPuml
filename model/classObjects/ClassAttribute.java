/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classObjects;

import model.diagram.DiagramObject;

/**
 *
 * @author
 * fvega
 */
public class ClassAttribute implements DiagramObject{
    private String name;
    private String type;

    @Override
    public String draw() {
        return "";
    }
}
