package de.abg.jreichert.naming;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.SimpleNameProvider;

import de.abg.jreichert.operaDSL.BookmarkFile;
import de.abg.jreichert.operaDSL.Folder;
import de.abg.jreichert.operaDSL.Link;

public class OperaSimpleNameProvider extends SimpleNameProvider {

	@Override
	public QualifiedName getFullyQualifiedName(EObject obj) {
		QualifiedName name = null;
		String nameSegment = null;
		if(obj instanceof BookmarkFile) {
			BookmarkFile bf = (BookmarkFile) obj;
			nameSegment = bf.eResource().getURI().path();
		} else if (obj instanceof Folder) {
			nameSegment = String.valueOf(((Folder) obj).getId());
		} else if (obj instanceof Link) {
			nameSegment = String.valueOf(((Link) obj).getId());
		}
		if(nameSegment != null) {
			name = QualifiedName.create(nameSegment);
		} else {
			name = super.getFullyQualifiedName(obj);
		}
		return name;
	}
}
