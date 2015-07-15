package model.fileHandler;

import java.util.List;

import model.diagram.DiagramObject;

import org.objectweb.asm.tree.ClassNode;

public interface ClassRelationParser {

	public List<DiagramObject> parse(ClassNode classNode, String projectPath);
	
}
