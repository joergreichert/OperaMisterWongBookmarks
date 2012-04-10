package de.abg.jreichert.ui.highlighting;

import org.eclipse.swt.SWT;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

public class WongHighlightingConfiguration extends DefaultHighlightingConfiguration {

	public static final String TODO_STYLE = "TODO_STYLE";
	
	@Override
	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		super.configure(acceptor);
		acceptor.acceptDefaultHighlighting(TODO_STYLE, TODO_STYLE, todoTextStyle());
	}
	
	@Override
	public TextStyle numberTextStyle() {
		return defaultTextStyle();
	}
	
	public TextStyle todoTextStyle() {
		TextStyle textStyle = commentTextStyle().copy();
		textStyle.setStyle(SWT.BOLD);
		return textStyle;
	}
	
}
