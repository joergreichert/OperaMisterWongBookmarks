package de.abg.jreichert.formatting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter.IFormattedRegion;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.io.Closer;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.abg.jreichert.OperaDSLInjectorProvider;

@RunWith(XtextRunner.class)
@InjectWith(OperaDSLInjectorProvider.class)
public class FormatterTest extends AbstractXtextTests {
    @Inject
    Injector injector;;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setInjector(injector);
    }

    @Inject
    protected INodeModelFormatter formatter;

    public String formatted(String path) throws Exception {
        Closer closer = Closer.create();
        try {
            FileInputStream fis = closer.register(new FileInputStream(new File(path)));
            ICompositeNode rootNode = super.getRootNode(fis);
            IFormattedRegion r = formatter.format(rootNode, 0, rootNode.getLength());
            return r.getFormattedText();
        } finally {
            closer.close();
        }
    }

    @Test
    @Ignore("not finished yet.")
    public void testFormatter() throws Exception {
        String expectedPath = "res/formatter/formatted/mdsd.adr";
        String expected = getFileContent(expectedPath);
        String formatted = formatted("res/formatter/unformatted/mdsd.adr");
        Assert.assertEquals(expected, formatted);
    }

    private String getFileContent(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(new File(path)), "UTF-8");
        String given = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return given;
    }
}
