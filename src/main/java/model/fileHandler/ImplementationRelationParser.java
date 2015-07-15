package model.fileHandler;

import java.util.ArrayList;
import java.util.List;

import model.diagram.DiagramObject;
import model.relationObjects.ClassRelation;
import model.relationObjects.ImplementationRelation;

import org.objectweb.asm.tree.ClassNode;
import util.Util;

public class ImplementationRelationParser implements ClassRelationParser {

	@Override
	public List<DiagramObject> parse(ClassNode classNode, String fooProjectPath) {
		List<String> classNames = classNode.interfaces;
		
		List<DiagramObject> relations = new ArrayList<>();
        for(String className: classNames) {
            ClassRelation classRelation = new ImplementationRelation();
            classRelation.setRelation(Util.cleanClassName(className));
            relations.add(classRelation);
        }

        return relations;
	}
	
}
