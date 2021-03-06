/*
 * generated by Xtext
 */
package de.abg.jreichert.formatting;

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import de.abg.jreichert.services.MisterWongDslGrammarAccess;

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an
 * example
 */
public class MisterWongDslFormatter extends AbstractDeclarativeFormatter {

	@Override
	protected void configureFormatting(FormattingConfig c) {
		MisterWongDslGrammarAccess mwga = (MisterWongDslGrammarAccess) getGrammarAccess();

		c.setIndentationIncrement().before(
				mwga.getBookmarkFileAccess().getPKeyword_8());
		c.setIndentationDecrement().after(
				mwga.getBookmarkFileAccess().getDLKeyword_11());

		c.setIndentationIncrement().around(mwga.getLinkRule());

		c.setIndentationIncrement()
				.before(mwga.getLinkAccess().getAKeyword_1());
		c.setIndentationDecrement().after(
				mwga.getLinkAccess().getNameAssignment_14());

		c.setNoSpace().around(mwga.getLinkAccess().getEqualsSignKeyword_3());
		c.setNoSpace().around(mwga.getLinkAccess().getEqualsSignKeyword_6());
		c.setNoSpace().around(mwga.getLinkAccess().getEqualsSignKeyword_9());
		c.setNoSpace().around(mwga.getLinkAccess().getEqualsSignKeyword_12());

		c.setNoLinewrap().between(
				mwga.getLinkAccess().getEqualsSignKeyword_3(),
				mwga.getLinkAccess().getUrlSTRINGTerminalRuleCall_4_0());
		c.setNoLinewrap().between(
				mwga.getLinkAccess().getEqualsSignKeyword_6(),
				mwga.getLinkAccess().getAddDateSTRINGTerminalRuleCall_7_0());
		c.setNoLinewrap().between(
				mwga.getLinkAccess().getEqualsSignKeyword_9(),
				mwga.getLinkAccess()
						.getLastModifiedSTRINGTerminalRuleCall_10_0());
		c.setNoLinewrap().between(
				mwga.getLinkAccess().getEqualsSignKeyword_12(),
				mwga.getLinkAccess().getTagsSTRINGTerminalRuleCall_13_0());

		c.setIndentationIncrement().after(mwga.getLinkAccess().getAKeyword_1());
		c.setIndentationDecrement().after(
				mwga.getLinkAccess().getNameAssignment_14());
		c.setIndentationDecrement().after(
				mwga.getLinkAccess().getDTKeyword_15());
	}
}
