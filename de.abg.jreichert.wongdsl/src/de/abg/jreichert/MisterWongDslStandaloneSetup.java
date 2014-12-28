package de.abg.jreichert;

import org.eclipse.xtext.ISetup;

/**
 * Initialization support for running Xtext languages without equinox extension registry
 */
public class MisterWongDslStandaloneSetup extends MisterWongDslStandaloneSetupGenerated implements ISetup {

    public static void doSetup() {
        new MisterWongDslStandaloneSetup().createInjectorAndDoEMFRegistration();
    }
}
