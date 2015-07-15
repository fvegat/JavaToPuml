/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.fileHandler.CompositionRelationParser;
import model.fileHandler.DependencyRelationParser;
import model.fileHandler.ImplementationRelationParser;
import model.fileHandler.InheritanceRelationParser;
import model.fileHandler.PumlWriter;
import model.diagram.DiagramObject;

import java.util.List;

import model.fileHandler.JavaClassParser;

/**
 *
 * @author
 * fvega
 */
public class Main {
    public static void main(String[] args) {
        try {
            
            String projectPath = args[0];
            String outPutFilePath = args[1];
            
            JavaClassParser javaFileParser = new JavaClassParser(projectPath);
            javaFileParser.addClassRelationParser(new CompositionRelationParser());
            javaFileParser.addClassRelationParser(new InheritanceRelationParser());
            javaFileParser.addClassRelationParser(new ImplementationRelationParser());
            javaFileParser.addClassRelationParser(new DependencyRelationParser());
            javaFileParser.parseJavaFiles();

            List<DiagramObject> classes = javaFileParser.getParsedClasses();

            PumlWriter pumlWriter = new PumlWriter();
            pumlWriter.writePuml(classes, outPutFilePath);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
