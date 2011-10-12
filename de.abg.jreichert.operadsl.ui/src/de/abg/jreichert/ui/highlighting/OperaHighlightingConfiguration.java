package de.abg.jreichert.ui.highlighting;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

public class OperaHighlightingConfiguration extends DefaultHighlightingConfiguration {

	@Override
	public TextStyle numberTextStyle() {
		return defaultTextStyle();
	}
}
