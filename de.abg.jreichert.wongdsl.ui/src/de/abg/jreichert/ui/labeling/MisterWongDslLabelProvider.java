package de.abg.jreichert.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.google.inject.Inject;

import de.abg.jreichert.misterWongDsl.BookmarkFile;
import de.abg.jreichert.misterWongDsl.Link;

/**
 * Provides labels for a EObjects.
 * 
 * see
 * http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class MisterWongDslLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	public MisterWongDslLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	String text(BookmarkFile ele) {
		return ele.eResource().getURI().lastSegment();
	}

	String text(Link ele) {
		return ele.getName();
	}

	String image(BookmarkFile ele) {
		return "wong.png";
	}

	String image(Link ele) {
		return "wong.png";
	}
}
