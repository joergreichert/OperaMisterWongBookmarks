package de.abg.jreichert.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IFileSystemAccess
import de.abg.jreichert.operaDSL.Element
import de.abg.jreichert.operaDSL.Link
import de.abg.jreichert.operaDSL.Folder
import de.abg.jreichert.operaDSL.BookmarkFile
import java.util.Collection
import java.util.ArrayList
import static extension org.eclipse.xtext.xbase.lib.IterableExtensions.*
import com.google.inject.Inject

class OperaDSLGenerator implements IGenerator {
	
	@Inject extension SortHelper sortHelper
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		for(file : resource.allContents.toIterable.filter(typeof(BookmarkFile)))
			fsa.generateFile(file.eResource.URI.trimFileExtension.lastSegment + "_opera.txt", addressFile(file))
	}
	
	def addressFile(BookmarkFile file) {
		val sb = new StringBuilder();
		val list = new ArrayList<String>()
		list.add("  ")
		for (element : file.elements.sortByTypeAndName) 
			print(sb, list, element)
		sb.toString
	}
	
	def dispatch void print(StringBuilder sb, Collection<String> spaces, Folder folder) {
		for (space : spaces) 
			sb.append(space) 
		sb.append("* Folder " + folder.name + "\n");
		val newSpaces = new ArrayList<String>()
		newSpaces.addAll(spaces)
		newSpaces.add("  ")
		for (element : folder.elements.sortByTypeAndName)
			print(sb, newSpaces, element)
	}

	def dispatch void print(StringBuilder sb, Collection<String> spaces, Link link) {
		for (space : spaces) 
			sb.append(space) 
		sb.append("* [" + link.url + " " + link.name + "]\n");
	}

	def dispatch void print(StringBuilder sb, Collection<String> spaces, Element folder) {
	}
}
