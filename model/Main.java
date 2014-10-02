/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.fileHandler.JavaParser;
import model.fileHandler.ParserFactory;
import model.fileHandler.PumlWriter;
import java.util.List;
import model.classObjects.ClassObject;

/**
 *
 * @author
 * fvega
 */
public class Main {
    public static void main(String[] args) {
        try {
            
            String projectPath = "/home/fvega/Proyectos/JavaToPuml2/build/classes/";             
                        
            JavaParser javaFileParser = ParserFactory.getInstance("");            
            List<ClassObject> classes = javaFileParser.parseJavaFiles(projectPath);
                        
            PumlWriter pumlWriter = new PumlWriter();
            pumlWriter.writePuml(classes);
                        
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.getLocalizedMessage());
        }
    }
}
