package model.fileHandler;

import java.util.ArrayList;
import java.util.List;

import model.diagram.DiagramObject;
import model.relationObjects.ClassRelation;
import model.relationObjects.DependencyRelation;

import org.objectweb.asm.tree.ClassNode;
import org.pf.tools.cda.base.model.workset.*;
import org.pf.tools.cda.base.model.Workset;
import org.pf.tools.cda.base.model.ClassInformation;
import org.pf.tools.cda.core.init.*;

import util.Util;


public class DependencyRelationParser implements ClassRelationParser {

	@Override
	public List<DiagramObject> parse(ClassNode classNode, String projectPath) {
		
		List<DiagramObject> relations = new ArrayList<>();
		
		ClassInformation[] classes = this.findClasses(classNode, projectPath);
		
		for(ClassInformation clazz : classes ) {
			ClassRelation classRelation = new DependencyRelation();
			String className = Util.cleanClassName(clazz.getName());
			classRelation.setRelation(className);
			relations.add(classRelation);
		}

		return relations;
	}

	private ClassInformation[] findClasses(ClassNode classNode, String projectPath) {
		ClasspathPartDefinition partDefinition = new ClasspathPartDefinition(projectPath);
		Workset workset = new Workset("Foo");
		workset.addClasspathPartDefinition(partDefinition);
		WorksetInitializer wsInitializer = new WorksetInitializer(workset);
		wsInitializer.initializeWorksetAndWait(null);
		ClassInformation classInfo = workset.getClassInfo(classNode.name.replace("/", "."));
		ClassInformation[] classes = classInfo.getReferredClassesArray();
		return classes;
	}
	

}