package de.abg.jreichert.generator

import java.util.Collection
import de.abg.jreichert.operaDSL.Element
import de.abg.jreichert.operaDSL.Folder

class SortHelper {
	
	def Collection<Element> sortByTypeAndName(Collection<Element> elements) {
		elements.sortWith([ a, b | compareElements(a,b) ])
	}
	
	def int compareElements(Element a, Element b) {
		val type = compareTypes(a, b)
		if(type == 0) {
			compareNames(a, b)
		} else {
			type
		}
	}
	
	def int compareTypes(Element a, Element b) {
		val aTypeIsFolder = a instanceof Folder;
		val bTypeIsFolder = b instanceof Folder;
		if(aTypeIsFolder == bTypeIsFolder) {
			0
		} else if(aTypeIsFolder) {
			-1
		} else {
			1
		}
	}

	def int compareNames(Element a, Element b) {
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
}