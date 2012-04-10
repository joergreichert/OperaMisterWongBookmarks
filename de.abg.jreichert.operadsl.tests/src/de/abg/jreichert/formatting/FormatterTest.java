package de.abg.jreichert.formatting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.parameterized.InjectParameter;
import org.eclipse.xtext.junit4.parameterized.ParameterizedXtextRunner;
import org.eclipse.xtext.junit4.parameterized.ResourceURIs;
import org.eclipse.xtext.junit4.validation.AbstractValidatorTester;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import de.abg.jreichert.OperaDSLInjectorProvider;


@RunWith(ParameterizedXtextRunner.class)
@InjectWith(OperaDSLInjectorProvider.class)
@ResourceURIs(baseDir = "res/formatter/unformatted", fileExtensions = "adr")
@Ignore
public class FormatterTest extends AbstractValidatorTester {
 
	@InjectParameter
	protected XtextResource resource;
 
	@Inject
	protected INodeModelFormatter formatter;
 
	public String formatted() {
		ICompositeNode rootNode = resource.getParseResult().getRootNode();
		IFormattedRegion r = formatter.format(rootNode, 0, rootNode.getLength());
		return r.getFormattedText();
	}
	
	@Test
	@Ignore
	public void testFormatter() throws Exception {
		String expectedPath = "res/formatter/formatted/mdsd.adr"; 
		String expected = getFileContent(expectedPath);
		String formatted = formatted();
		Assert.assertEquals(expected, formatted);
	}

	private String getFileContent(String path) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream(new File(path)), "UTF-8");
		String given = scanner.useDelimiter("\\Z").next();
		scanner.close();
		return given;
	}
}
