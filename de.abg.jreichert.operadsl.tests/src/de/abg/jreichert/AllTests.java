package de.abg.jreichert;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.abg.jreichert.formatting.FormatterTest;

@RunWith(Suite.class)
@SuiteClasses({ FormatterTest.class })
public class AllTests {

}
