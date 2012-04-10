package de.abg.jreichert.ui.highlighting;

import java.util.List;

import org.eclipse.core.internal.registry.OffsetTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import com.google.inject.Inject;

import de.abg.jreichert.misterWongDsl.BookmarkFile;
import de.abg.jreichert.misterWongDsl.Link;
import de.abg.jreichert.misterWongDsl.MisterWongDslPackage;
import de.abg.jreichert.services.MisterWongDslGrammarAccess;

public class WongSemanticHighlightingCalculator implements
		ISemanticHighlightingCalculator {

	@Inject
	MisterWongDslGrammarAccess mwga;

	public void provideHighlightingFor(XtextResource resource,
			IHighlightedPositionAcceptor acceptor) {
		if (resource == null) {
			return;
		}
		EList<EObject> contents = resource.getContents();
		if (contents.size() == 0) {
			return;
		}
		EObject root = contents.get(0);
		if (root instanceof BookmarkFile) {
			BookmarkFile file = (BookmarkFile) root;
			ICompositeNode node = NodeModelUtils.findActualNodeFor(root);
			highlightTitle(file, node, acceptor);
			highlightHeader(file, node, acceptor);
			for (Link link : file.getLinks()) {
				highlightLinkName(link, node, acceptor);
				highlightLinkDescription(link, node, acceptor);
				highlightLinkTagsInDescription(link, node, acceptor);
			}
		}
	}

	private void highlightLinkName(Link link, ICompositeNode node,
			IHighlightedPositionAcceptor acceptor) {
		highlight(link, MisterWongDslPackage.LINK__NAME, mwga.getLinkAccess()
				.getNameLINK_NAMETerminalRuleCall_14_0(), node, acceptor, ">",
				"</A>");
	}

	private void highlightLinkTagsInDescription(Link link, ICompositeNode node,
			IHighlightedPositionAcceptor acceptor) {
		highlight(link, MisterWongDslPackage.LINK__DESCRIPTION, mwga
				.getLinkAccess()
				.getDescriptionDESCRIPTIONTerminalRuleCall_16_0(), node,
				acceptor, "tags:");
	}
	
	private void highlightLinkDescription(Link link, ICompositeNode node,
			IHighlightedPositionAcceptor acceptor) {
		highlight(link, MisterWongDslPackage.LINK__DESCRIPTION, mwga
				.getLinkAccess()
				.getDescriptionDESCRIPTIONTerminalRuleCall_16_0(), node,
				acceptor, "<DD>", "</DD>");
	}

	private void highlightTitle(BookmarkFile file, ICompositeNode node,
			IHighlightedPositionAcceptor acceptor) {
		highlight(file, MisterWongDslPackage.BOOKMARK_FILE__NAME, mwga
				.getBookmarkFileAccess().getNameTITLETerminalRuleCall_5_0(),
				node, acceptor, "<TITLE>", "</TITLE>");
	}

	private void highlightHeader(BookmarkFile file, ICompositeNode node,
			IHighlightedPositionAcceptor acceptor) {
		highlight(file, MisterWongDslPackage.BOOKMARK_FILE__HEADER, mwga
				.getBookmarkFileAccess().getHeaderHEADERTerminalRuleCall_6_0(),
				node, acceptor, "<H1>", "</H1>");
	}

	private void highlight(EObject root, int eStructuralFeatureId,
			EObject grammarElement, ICompositeNode node,
			IHighlightedPositionAcceptor acceptor, String... tokens) {
		EObject ge = null;
		List<INode> nodes = NodeModelUtils.findNodesForFeature(root, root
				.eClass().getEStructuralFeature(eStructuralFeatureId));
		for (INode child : nodes) {
			if (child.getText() != null) {
				ge = child.getGrammarElement();
				if (ge.equals(grammarElement)) {
					highlight(acceptor, ge, child, tokens);
				}
			}
		}
	}

	private void highlight(IHighlightedPositionAcceptor acceptor, EObject ge,
			INode child, String... tokens) {
		String text;
		Assignment assignment = GrammarUtil.containingAssignment(ge);
		if (assignment != null) {
			text = child.getText();
			for (String token : tokens) {
				addPosition(acceptor, child, text, token);
			}
		}
	}

	private void addPosition(IHighlightedPositionAcceptor acceptor,
			INode child, String text, String token) {
		if (text.contains(token)) {
			int start = child.getOffset() + text.indexOf(token);
			if (start >= 0 && text.length() <= start + token.length()) {
				acceptor.addPosition(start, token.length(),
						WongHighlightingConfiguration.KEYWORD_ID);
			}
		}
	}
}
