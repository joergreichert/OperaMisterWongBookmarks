package de.abg.jreichert.conversion;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;
import org.eclipse.xtext.nodemodel.INode;

public class MisterWongValueConverterService extends
		AbstractDeclarativeValueConverterService {
	
	private class MisterWongValueConverter implements IValueConverter<String> {
		private final String startToken, endToken;
		private final int startTokenLen, endTokenLen;
		
		MisterWongValueConverter(String startToken, String endToken) {
			super();
			this.startToken = startToken;
			this.endToken = endToken;
			startTokenLen = startToken.length();
			endTokenLen = endToken.length();
		}

		@Override
		public String toValue(String string, INode node)
				throws ValueConverterException {
			return string.length() >= (startTokenLen + endTokenLen) ? string.substring(startTokenLen, string.length()-endTokenLen) : string;
		}

		@Override
		public String toString(String value) throws ValueConverterException {
			return startToken + value + endToken;
		}
	}

	@ValueConverter(rule = "LINK_NAME")
	public IValueConverter<String> getLinkNameConverter() {
		return new MisterWongValueConverter(">", "</A>");
	}
	
	@ValueConverter(rule = "TITLE")
	public IValueConverter<String> getTitleConverter() {
		return new MisterWongValueConverter("<TITLE>", "</TITLE>");
	}
	
	@ValueConverter(rule = "HEADER")
	public IValueConverter<String> getHeaderConverter() {
		return new MisterWongValueConverter("<H1>", "</H1>");
	}
	
	@ValueConverter(rule = "DESCRIPTION")
	public IValueConverter<String> getDescriptionConverter() {
		return new MisterWongValueConverter("<DD>", "</DD>");
	}
}
