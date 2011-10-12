package de.abg.jreichert.ui.highlighting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import com.google.inject.Inject;

import de.abg.jreichert.operaDSL.BookmarkFile;
import de.abg.jreichert.operaDSL.Element;
import de.abg.jreichert.operaDSL.Folder;
import de.abg.jreichert.operaDSL.Link;
import de.abg.jreichert.services.OperaDSLGrammarAccess;

public class OperaHighlightingCalculator implements
		ISemanticHighlightingCalculator {

	@Inject
	OperaDSLGrammarAccess access;

	public void provideHighlightingFor(XtextResource resource,
			IHighlightedPositionAcceptor acceptor) {
		if (resource.getContents().size() > 0) {
			BookmarkFile operaBookmarkFile = (BookmarkFile) resource
					.getContents().get(0);
			for (Element elem : operaBookmarkFile.getElements()) {
				if (elem instanceof Link) {
					visitLink(acceptor, (Link) elem);
				} else if (elem instanceof Folder) {
					visitFolder(acceptor, (Folder) elem);
				}
			}
		}
	}

	private void visitFolder(IHighlightedPositionAcceptor acceptor,
			Folder folder) {
		for (Element elem : folder.getElements()) {
			if (elem instanceof Link) {
				visitLink(acceptor, (Link) elem);
			} else {
				visitFolder(acceptor, (Folder) elem);
			}
		}
	}

	private void visitLink(IHighlightedPositionAcceptor acceptor, Link link) {
		INode node = NodeModelUtils.getNode(link);
		for (ILeafNode leafNode : node.getLeafNodes()) {
			if (leafNode.getGrammarElement() instanceof Keyword) {
				Keyword kw = (Keyword) leafNode.getGrammarElement();
				if("URL".equals(kw.getValue())) {
					INode equalsNode = leafNode.getNextSibling();
					if(equalsNode != null) {
						INode urlValueNode = equalsNode.getNextSibling();
						if(urlValueNode != null) {
							acceptor.addPosition(urlValueNode.getOffset(),
									urlValueNode.getLength(),
									OperaHighlightingConfiguration.URL);
						}
					}
				}
			}
		}
	}
}
