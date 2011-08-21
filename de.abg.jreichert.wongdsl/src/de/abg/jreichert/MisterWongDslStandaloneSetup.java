
package de.abg.jreichert;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class MisterWongDslStandaloneSetup extends MisterWongDslStandaloneSetupGenerated{

	public static void doSetup() {
		new MisterWongDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

