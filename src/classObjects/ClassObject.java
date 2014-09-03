/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.classObjects;

import java.util.List;
import src.DiagramObject;

/**
 *
 * @author
 * fvega
 */
public abstract class ClassObject implements DiagramObject {
    protected String name;
    protected List<DiagramObject> relations;
}
