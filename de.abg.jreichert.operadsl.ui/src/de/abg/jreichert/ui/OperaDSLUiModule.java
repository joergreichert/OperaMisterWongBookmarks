/*
 * generated by Xtext
 */
package de.abg.jreichert.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.DerivedResourceMarkers;
import org.eclipse.xtext.generator.IDerivedResourceMarkers;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import com.google.inject.Binder;

import de.abg.jreichert.ui.highlighting.OperaHighlightingCalculator;
import de.abg.jreichert.ui.highlighting.OperaHighlightingConfiguration;

/**
 * Use this class to register components to be used within the IDE.
 */
public class OperaDSLUiModule extends
		de.abg.jreichert.ui.AbstractOperaDSLUiModule {
	
	public OperaDSLUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(IHighlightingConfiguration.class).toInstance(new OperaHighlightingConfiguration());
		binder.bind(ISemanticHighlightingCalculator.class).toInstance(new OperaHighlightingCalculator());
		binder.bind(IDerivedResourceMarkers.class).toInstance(new DerivedResourceMarkers());
	}
	
	
}
