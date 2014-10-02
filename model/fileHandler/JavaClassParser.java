package model.fileHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import model.classObjects.ClassObject;
import model.classObjects.ClassTypeFactory;
import model.diagram.DiagramObject;
import model.relationObjects.ClassRelation;
import model.relationObjects.ImplementationRelation;
import model.relationObjects.InheritanceRelation;

public class JavaClassParser implements JavaParser{

    @Override
    public List<ClassObject> parseJavaFiles(String projectPath) throws Exception {        
        JavaClassLoader javaClassLoader = new JavaClassLoader(projectPath);
        List<Class> classes = javaClassLoader.getClassFiles();
        return parseClassToClassObjects(classes);
    }
    
    private List<ClassObject> parseClassToClassObjects(List<Class> classes) {
        List<ClassObject> parsedClasses = new ArrayList<>();
        
        for(Class rawClassObject: classes) {
            ClassObject classObject = ClassTypeFactory.getInstance(rawClassObject); //TODO: reemplazar con un factory
            classObject.setName(rawClassObject.getSimpleName());
            classObject.setRelations(this.parseClassRelations(rawClassObject));
            
            parsedClasses.add(classObject);
        }
        
        return parsedClasses;
        
    }   
    
    private List<DiagramObject> parseClassRelations(Class rawClassObject) {
        List<DiagramObject> classRelations = new ArrayList<>();
        classRelations.add(this.parseInheritance(rawClassObject));
        classRelations.addAll(this.parseImplementationRelation(rawClassObject));
        
        return classRelations;
    }
    
    private DiagramObject parseInheritance(Class rawClassObject) {
        ClassRelation classRelation = new InheritanceRelation();
        classRelation.setRelation(this.cleanClassName(rawClassObject.getGenericSuperclass().toString()));
        classRelation.setDrawable(true);        
        
        return classRelation;
    }
    
    private List<DiagramObject> parseImplementationRelation(Class rawClassObject) {
        List<DiagramObject> implemetationRelations = new ArrayList<>();
        for(Type interfaceType: rawClassObject.getGenericInterfaces()) {
            ClassRelation classRelation = new ImplementationRelation();
            classRelation.setRelation(this.cleanClassName(interfaceType.toString()));
            classRelation.setDrawable(true);
            implemetationRelations.add(classRelation);
        }
        
        return implemetationRelations;
    }
    
    private String cleanClassName(String className) {        

        if(className.contains("class"))
            className = className.replace("class", "").replace(" ", "");

        if(className.contains(".")) {
            String[] splittedClassName = className.split("\\.");
            int classNameLength = splittedClassName.length;

            return splittedClassName[classNameLength -1];
        }
        else
            return className;
    }     
}
