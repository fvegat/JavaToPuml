/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fileHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.classObjects.ClassObject;
import model.diagram.DiagramObject;
import model.relationObjects.ClassRelation;

/**
 *
 * @author
 * fvega
 */
public class PumlWriter {
    public void writePuml(List<DiagramObject> classes, String outPutFilePath) {
        FileWriter pumlFileWriter = null;
        try {
            pumlFileWriter = new FileWriter(outPutFilePath);
            pumlFileWriter.append("@startuml\n");
            
            this.writeClasses(classes, pumlFileWriter);
            this.writeRelations(classes, pumlFileWriter);
            pumlFileWriter.append("@enduml\n");
        } catch (IOException ex) {
            System.out.println("ERROR ESCRIBIENDO EL ARCHIVO");
        } finally {
            try {
                pumlFileWriter.close();
            } catch (IOException ex) {
                System.out.println("ERROR CERRANDO EL ARCHIVO");
            }
        }
    }
    
    private void writeClasses(List<DiagramObject> classes, FileWriter pumlFileWriter) throws IOException {
        for (DiagramObject diagramObject : classes) {
            pumlFileWriter.append(diagramObject.draw());
            if(!((ClassObject)diagramObject).getAttributes().isEmpty()) {
                pumlFileWriter.append(" {\n");
                this.writeAttributes(((ClassObject)diagramObject).getAttributes(), pumlFileWriter);
                pumlFileWriter.append("}");
            }            
            pumlFileWriter.append("\n");
        }
    }
    
    private void writeRelations(List<DiagramObject> relations, FileWriter pumlFileWriter) throws IOException {
        for (DiagramObject diagramObject : relations) {
            for (DiagramObject relation : ((ClassObject)diagramObject).getRelations()) {                
                if(((ClassRelation)relation).isDrawable())
                    pumlFileWriter.append(relation.draw() + " " + ((ClassObject)diagramObject).getName() + "\n");
            }
        }
    }
    
    private void writeAttributes(List<DiagramObject> attributes, FileWriter pumlFileWriter) throws IOException {
        for(DiagramObject diagramObject: attributes)
            pumlFileWriter.append("  " + diagramObject.draw() + "\n");        
    }
}
