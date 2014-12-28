package de.abg.jreichert;

import org.eclipse.xtext.ISetup;

/**
 * Initialization support for running Xtext languages without equinox extension registry
 */
public class OperaDSLStandaloneSetup extends OperaDSLStandaloneSetupGenerated implements ISetup {

    public static void doSetup() {
        new OperaDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
    }
}
