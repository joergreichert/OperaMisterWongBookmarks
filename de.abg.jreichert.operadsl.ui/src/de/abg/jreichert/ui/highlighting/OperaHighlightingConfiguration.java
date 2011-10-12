package de.abg.jreichert.ui.highlighting;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

public class OperaHighlightingConfiguration extends
		DefaultHighlightingConfiguration {

	public static final String URL = "URL";

	@Override
	public TextStyle numberTextStyle() {
		return defaultTextStyle();
	}

	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		super.configure(acceptor);
		acceptor.acceptDefaultHighlighting(URL, "Opera Bookmark URL", urlType());
	}

	public TextStyle urlType() {
		return stringTextStyle();
	}
}
