package model.fileHandler;

import java.util.ArrayList;
import java.util.List;

import model.diagram.DiagramObject;
import model.relationObjects.ClassRelation;
import model.relationObjects.InheritanceRelation;

import org.objectweb.asm.tree.ClassNode;

import util.Util;

public class InheritanceRelationParser implements ClassRelationParser {

	@Override
	public List<DiagramObject> parse(ClassNode classNode, String fooProjectPath) {
        List<String> classNames = new ArrayList<String>();
        classNames.add(classNode.superName);
        
		List<DiagramObject> relations = new ArrayList<>();
        for(String className: classNames) {
        	ClassRelation classRelation = new InheritanceRelation();
            classRelation.setRelation(Util.cleanClassName(className));
            relations.add(classRelation);
        }
        
        return relations;
	}
		
	
}
