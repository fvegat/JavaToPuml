/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.classObjects.ClassObject;

/**
 *
 * @author
 * fvega
 */
public class Main {
    public static void main(String[] args) {
        try {
            String projectPath = "/home/fvega/Proyectos/JavaToPuml2/src/";             
            
            List<ClassObject> classes;
            JavaFileParser javaFileParser = new JavaFileParser();
            List<String> filePaths = new ArrayList<>();
            getJavaSourceFiles(projectPath, filePaths);
            //classes = javaFileParser.parseJavaFiles(filePaths);
                        
            PumlWriter pumlWriter = new PumlWriter();
            //pumlWriter.writePuml(classes);
                        
        } catch (Exception ex) {
            System.out.println("ERROR" + ex.getLocalizedMessage());
        }
    }
    
    public static List<String> getJavaSourceFiles(String rootSourceDirectory, List<String> filePaths) {
        File root = new File(rootSourceDirectory);         
        try {
            List<File> files = new ArrayList<>(Arrays.asList(root.listFiles()));
            for (File file : files) {
                if(file.isDirectory())
                    getJavaSourceFiles(file.getAbsolutePath(), filePaths);
                else {
                    if(file.getAbsolutePath().endsWith(".java"))
                        filePaths.add(file.getAbsolutePath());                    
                }
            }
            
            return filePaths;
        }            
        catch (Exception ex) {
            throw ex;
        }            
        
    }
}
