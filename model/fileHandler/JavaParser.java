/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fileHandler;

import java.util.List;
import model.classObjects.ClassObject;

/**
 *
 * @author
 * fvega
 */
public interface JavaParser {
    public List<ClassObject> parseJavaFiles(String projectPath) throws Exception;
}
