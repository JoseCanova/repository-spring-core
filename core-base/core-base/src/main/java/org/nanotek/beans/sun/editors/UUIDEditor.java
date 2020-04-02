package org.nanotek.beans.sun.editors;

import java.beans.PropertyEditorSupport;
import java.util.UUID;

public class UUIDEditor extends PropertyEditorSupport{

    public String getJavaInitializationString() {
        Object value = getValue();
        if (value == null)
            return "null";
        else
        	return  value.toString();
    }
    
    public void setAsText(String text) {
        setValue((text == null) ? null : UUID.fromString(text));
    }
    
    @Override
    public Object getValue() {
    	return super.getValue();
    }
    		
}
