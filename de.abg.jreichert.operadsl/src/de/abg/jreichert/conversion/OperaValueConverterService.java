package de.abg.jreichert.conversion;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;
import org.eclipse.xtext.nodemodel.INode;

public class OperaValueConverterService extends
		AbstractDeclarativeValueConverterService {
	private static OperaValueConverter converter = new OperaValueConverter();
	
	private static class OperaValueConverter implements IValueConverter<String> {
		
		@Override
		public String toValue(String string, INode node)
				throws ValueConverterException {
			return string.trim();
		}

		@Override
		public String toString(String value) throws ValueConverterException {
			return value.trim();
		}
	}

	@ValueConverter(rule = "StringWithoutQuotes")
	public IValueConverter<String> getLinkNameConverter() {
		return converter;
	}
}
