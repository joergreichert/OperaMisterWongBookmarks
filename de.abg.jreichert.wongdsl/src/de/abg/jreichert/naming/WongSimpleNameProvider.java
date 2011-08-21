package de.abg.jreichert.naming;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.SimpleNameProvider;

import de.abg.jreichert.misterWongDsl.BookmarkFile;
import de.abg.jreichert.misterWongDsl.Link;

public class WongSimpleNameProvider extends SimpleNameProvider {

	@Override
	public QualifiedName getFullyQualifiedName(EObject obj) {
		QualifiedName name = null;
		String nameSegment = null;
		if(obj instanceof BookmarkFile) {
			BookmarkFile bf = (BookmarkFile) obj;
			nameSegment = bf.eResource().getURI().path();
		} else if (obj instanceof Link) {
			Link link = (Link) obj;
			nameSegment = link.getName() + "$$" + link.getAddDate();
		}
		if(nameSegment != null) {
			name = QualifiedName.create(nameSegment);
		} else {
			name = super.getFullyQualifiedName(obj);
		}
		return name;
	}
}
