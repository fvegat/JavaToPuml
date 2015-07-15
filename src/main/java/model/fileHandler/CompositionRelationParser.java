package model.fileHandler;

import java.util.ArrayList;
import java.util.List;

import model.diagram.DiagramObject;
import model.relationObjects.CompositionRelation;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import util.Util;

public class CompositionRelationParser implements ClassRelationParser {

	@Override
	public List<DiagramObject> parse(ClassNode classNode, String fooProjectPath) {
    	
		List<DiagramObject> relations = new ArrayList<>();
    	
		List<FieldNode> fields = classNode.fields;
        for (FieldNode field : fields) {
        	CompositionRelation compositionRelation = new CompositionRelation();
            compositionRelation.setRelation(this.parseAttributeType(field.desc, field.signature).replace("List<", "").replace(">", ""));
            if(!relations.contains(compositionRelation)  ) {          
            	relations.add(compositionRelation);
            }
        }    	
    	return relations;
	}

	private String parseAttributeType(String name, String type) {
    	name = Util.cleanClassName(name);
    	if (type != null && type.contains("List")) {
    		name += "<" + Util.cleanClassName(type.split("<")[1]) + ">";
    	}
    	
    	return name;
    }
	
}
