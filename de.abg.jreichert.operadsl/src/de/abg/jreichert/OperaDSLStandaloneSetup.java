
package de.abg.jreichert;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class OperaDSLStandaloneSetup extends OperaDSLStandaloneSetupGenerated{

	public static void doSetup() {
		new OperaDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

