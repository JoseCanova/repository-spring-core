package org.nanotek.beans.sun.editors;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQueries;
import java.util.Optional;

public class LocalDateEditor extends PropertyEditorSupport {
	
    public String getJavaInitializationString() {
        Object value = getValue();
        if (value == null)
            return "null";
        else
        	return  value.toString();
    }
    
    public void setAsText(String text) {
        setValue((text == null) ? null : LocalDateEditor.fromString(text));
    }
    

    @Override
    public Object getValue() {
    	return super.getValue();
    }

	
	private static DateTimeFormatter formatter =  Optional.ofNullable("").map(m->{
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("kk:mm:ss");
		DateTimeFormatter zoneFormatter = DateTimeFormatter.ofPattern("X");
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
												.append(yearFormatter)
												.appendLiteral(' ')
												.append(timeFormatter)
												.appendFraction(ChronoField.NANO_OF_SECOND, 6, 6, true)
												.append(zoneFormatter);
		return builder.toFormatter();
	}).get(); 
	
	static LocalDate fromString(String value) { 
		try {
			return value !=null && !value.isEmpty()?  LocalDateEditor.formatter.parse(value).query(TemporalQueries.localDate()) : null;
			}catch(Exception  ex) { 
				ex.printStackTrace();
			}
			return null;
	}
}
