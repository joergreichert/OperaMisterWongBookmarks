package de.abg.jreichert.formatting;

import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.runner.RunWith;
import org.xpect.expectation.IStringExpectation;
import org.xpect.expectation.StringExpectation;
import org.xpect.parameter.ParameterParser;
import org.xpect.runner.Xpect;
import org.xpect.runner.XpectRunner;
import org.xpect.runner.XpectTestFiles;
import org.xpect.runner.XpectTestFiles.FileRoot;
import org.xpect.setup.XpectSetup;
import org.xpect.xtext.lib.setup.ThisOffset;
import org.xpect.xtext.lib.setup.ThisResource;
import org.xpect.xtext.lib.setup.XtextStandaloneSetup;
import org.xpect.xtext.lib.setup.XtextValidatingSetup;

import com.google.inject.Inject;

import de.abg.jreichert.formatting.FormatterTest.NullValidatorSetup;

@RunWith(XpectRunner.class)
@XpectTestFiles(relativeTo = FileRoot.PROJECT, baseDir = "res/formatter/unformatted", fileExtensions = "adr")
@XpectSetup({ XtextStandaloneSetup.class, NullValidatorSetup.class })
public class FormatterTest {
	private static String UNIX_LINE_ENDING = "\n";
	private static String WINDOWS_LINE_ENDING = "\r\n";
	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	public static class NullValidatorSetup extends XtextValidatingSetup {
		public NullValidatorSetup(@ThisResource XtextResource resource) {
			super(resource);
		}

		@Override
		public void validate() {}
	}

	@Inject
	private INodeModelFormatter formatter;

	@ParameterParser(syntax = "('from' offset=OFFSET 'to' to=OFFSET)?")
	@Xpect
	public void formatted(@StringExpectation(whitespaceSensitive = true) IStringExpectation expectation,
			@ThisResource XtextResource resource, @ThisOffset int offset, @ThisOffset int to) {
		ICompositeNode rootNode = resource.getParseResult().getRootNode();
		IFormattedRegion r = null;
		if (offset >= 0 && to > offset) {
			r = formatter.format(rootNode, offset, to - offset);
		} else {
			r = formatter.format(rootNode, rootNode.getOffset(), rootNode.getTotalLength());
		}
		String formatted = r.getFormattedText();
		if (isUnixLineEnding()) {
			formatted = formatted.replaceAll(WINDOWS_LINE_ENDING, UNIX_LINE_ENDING);
		} else if (isWindowsLineEnding()) {
			if (!rootNode.getText().contains(WINDOWS_LINE_ENDING)) {
				formatted = formatted.replaceAll(WINDOWS_LINE_ENDING, UNIX_LINE_ENDING);
			} else {
				formatted = formatted.replaceAll("(!\r)\n", WINDOWS_LINE_ENDING);
			}
		}
		expectation.assertEquals(formatted);
	}

	private static boolean isWindowsLineEnding() {
		return WINDOWS_LINE_ENDING.equals(LINE_SEPARATOR);
	}

	private static boolean isUnixLineEnding() {
		return UNIX_LINE_ENDING.equals(LINE_SEPARATOR);
	}
}