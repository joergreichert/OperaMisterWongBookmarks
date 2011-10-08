package de.abg.jreichert.forms.ui.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.eclipse.core.databinding.conversion.IConverter;

public class OperaToTimestampConverter implements IConverter {

	@Override
	public Object getFromType() {
		return null;
	}

	@Override
	public Object getToType() {
		return null;
	}

	@Override
	public Object convert(Object fromObject) {
		Object converted = null;
		if (fromObject instanceof Integer) {
			converted = convertIntegerToString((Integer) fromObject);
		} else if (fromObject instanceof String) {
			converted = convertStringToInteger((String) fromObject);
		}
		return converted;
	}

	private Object convertStringToInteger(String formated) {
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM);
		try {
			date = df.parse(formated);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	private Object convertIntegerToString(Integer value) {
		Date date = new Date(value);
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.set(2007, 10, 7);
//		long time = cal.getTime().getTime();
		return df.format(date);
	}
}
