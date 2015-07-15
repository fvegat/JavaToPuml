package model.fileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import model.classObjects.AttributeTypeFactory;
import model.classObjects.ClassAttribute;
import model.classObjects.ClassObject;
import model.classObjects.ClassTypeFactory;
import model.diagram.DiagramObject;
import model.relationObjects.ClassRelation;
import constants.ComplexAttributeNameTypes;

public class JavaClassParser {

	private JavaClassLoader javaClassLoader;
	private List<DiagramObject> parsedClasses;
	private String projectPath;
	private List<ClassRelationParser> classRelationParsers;

	public JavaClassParser(String projectPath) {
		this.javaClassLoader = new JavaClassLoader();
		this.parsedClasses = new ArrayList<DiagramObject>();
		this.projectPath = projectPath;
		this.classRelationParsers = new ArrayList<ClassRelationParser>();
	}

    public List<DiagramObject> getParsedClasses() {
    	return this.parsedClasses;
    }
	
    public void parseJavaFiles() throws Exception {        
        List<File> classFiles = this.javaClassLoader.getClassFiles(this.projectPath);
        
        for(File classFile: classFiles) {
            ClassReader classReader = new ClassReader(new FileInputStream(classFile));
            ClassNode classNode = new ClassNode(Opcodes.ASM4);                      
            classReader.accept(classNode, 0);
            ClassObject classObject = ClassTypeFactory.getInstance(classNode);
            classObject.setName(this.parseClassName(classReader));
            classObject.setAttributes(this.parseAttributes(classNode));
            classObject.setRelations(this.parseClassRelations(classNode));            
            this.parsedClasses.add(classObject);
            System.out.println("PARSED: " + classObject.getName());
        }
        this.setDrawableObjects(parsedClasses);
    }
    
    private String parseClassName(ClassReader classReader) throws FileNotFoundException, IOException {                
        String className = classReader.getClassName();
        return this.cleanClassName(className);        
    }

    private List<DiagramObject> parseClassRelations(ClassNode classNode) {
        List<DiagramObject> classRelations = new ArrayList<>();
    	
    	for (ClassRelationParser relationParser: this.classRelationParsers) {
    		List<DiagramObject> newClassRelations = relationParser.parse(classNode, this.projectPath); 
    		for (DiagramObject newClassRelation : newClassRelations) {
    			if (!isDuplicatedDependencyRelation(newClassRelation, classRelations)) {
    				classRelations.add(newClassRelation);
    			}
    			
    		}
    	}
        return classRelations;
    }

	private boolean isDuplicatedDependencyRelation(DiagramObject newClassRelation, List<DiagramObject> classRelations) {
		String relationType = newClassRelation.getClass().getSimpleName();
		if (relationType.equals("DependencyRelation")) {
			String newClassName = ((ClassRelation)newClassRelation).getRelation();
			for (DiagramObject classRelation: classRelations) {
				if (((ClassRelation)classRelation).getRelation().equals(newClassName)) {
					return true;
				}
			}
		}
		return false;
	}

	private List<DiagramObject> parseAttributes(ClassNode classNode) {
        List<DiagramObject> attributes = new ArrayList();
        List<FieldNode> fields = classNode.fields;
        for (FieldNode field : fields) {            
            ClassAttribute classAttribute = AttributeTypeFactory.getInstance(field);
            classAttribute.setName(field.name);
            classAttribute.setType(this.cleanAttributeName(field.desc, field.signature));
            classAttribute.setComplexType(field.signature);
            attributes.add(classAttribute);
        }
        return attributes;
    }
    
    private String cleanClassName(String className) {
        String[] splittedName = className.split("/");
        return splittedName[splittedName.length -1].replace(";", "");                
    }
  
    private String cleanAttributeName(String attributeName, String signature) {
        if(attributeName.equals("Z"))
        	return ComplexAttributeNameTypes.BOOLEAN;
        
        else if(signature == null) {        
            String[] splittedAttributeName = attributeName.split("/");
            return splittedAttributeName[splittedAttributeName.length -1].replace(";", "");
        }
        else {
            String[] splittedAttributeName = signature.split("/");
            return splittedAttributeName[splittedAttributeName.length -1].replace(";", "").replace("<", "").replace(">", "");
        }
    }
    
    private void setDrawableObjects(List<DiagramObject> objectsToDraw) {
        
        for (DiagramObject classObject : objectsToDraw) {
            for(DiagramObject classRelation: ((ClassObject)classObject).getRelations()) {
                String relationName = ((ClassRelation)classRelation).getRelation();
                for(DiagramObject objectToCompare: objectsToDraw) {
                	if(((ClassObject)objectToCompare).getName().equals(relationName))
                        ((ClassRelation)classRelation).setDrawable(true);
                }
            }
        }
    }
    
    public void addClassRelationParser(ClassRelationParser classRelationParser) {
		this.classRelationParsers.add(classRelationParser);		
	}
}
