/*
 * generated by Xtext
 */
package de.abg.jreichert;

import org.eclipse.xtext.conversion.IValueConverterService;

import de.abg.jreichert.conversion.OperaValueConverterService;
import de.abg.jreichert.naming.OperaSimpleNameProvider;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class OperaDSLRuntimeModule extends de.abg.jreichert.AbstractOperaDSLRuntimeModule {
	
	public Class<? extends org.eclipse.xtext.naming.IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return OperaSimpleNameProvider.class;
	}
	
	@Override
	public Class<? extends IValueConverterService> bindIValueConverterService() {
		return OperaValueConverterService.class;
	}
}
