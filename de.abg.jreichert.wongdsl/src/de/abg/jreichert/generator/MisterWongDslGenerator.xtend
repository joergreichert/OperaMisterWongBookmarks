/*
 * generated by Xtext
 */
package de.abg.jreichert.generator

import de.abg.jreichert.misterWongDsl.BookmarkFile
import de.abg.jreichert.misterWongDsl.Link
import de.abg.jreichert.misterWongDsl.MisterWongDslFactory
import java.io.File
import java.io.IOException
import java.util.Collection
import java.util.Collections
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator

class MisterWongDslGenerator implements IGenerator {
		
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		for(file : resource.allContents.toIterable.filter(typeof(BookmarkFile))) {
			fsa.generateFile(file.eResource.URI.trimFileExtension.lastSegment + "_wong.txt", addressFile(file))
			mdsdFiltered(resource)
		}
	}
	
	def addressFile(BookmarkFile file) '''
		«FOR link : file.links.sortByName» 
			* Link «link.name»: «link.url»
		«ENDFOR»	
	'''
	
	def Collection<Link> sortByName(Collection<Link> links) {
		links.sortWith([ a, b | compareLinks(a,b) ])
	}

	def int compareLinks(Link a, Link b) {
		if(a.name == null) {
			if(b.name == null) {
				0
			} else {
				-1
			}
		} else {
			if(b.name == null) {
				1
			} else {
				a.name.toLowerCase.compareTo(b.name.toLowerCase)
			}
		}
	}
	
	def mdsdFiltered(Resource resource) {
		try {
			if(!resource.URI.path.contains("src-gen")) {
				val newUriStr = resource.URI.path
					.replace(".wong", "_filtered.wong")
					.replace("/resource", "")
					.replace("src", "src-gen")
				val newUri = URI.createPlatformResourceURI(newUriStr, true)
				if(new File(newUriStr).exists) {
					val res = resource.resourceSet.getResource(newUri, true)
					res.delete(Collections.EMPTY_MAP)
				}
				val newRes = resource.resourceSet.createResource(newUri)
				val root = resource.allContents.toIterable.filter(typeof(BookmarkFile)).head
				val newRoot = MisterWongDslFactory.eINSTANCE.createBookmarkFile
				newRoot.name = root.name
				newRoot.header = root.header
				newRoot.links.addAll(
					root.links.filter(link | 
						link.tags.split(" ").contains("modellgetriebene_entwicklung")
					).map(link | EcoreUtil.copy(link))
				)
				newRes.contents.add(newRoot)
				newRes.save(Collections.EMPTY_MAP)
			}
		} catch(IOException ioe) {
			ioe.printStackTrace
		}
	}
}
