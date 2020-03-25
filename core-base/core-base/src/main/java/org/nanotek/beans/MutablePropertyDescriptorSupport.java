package org.nanotek.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;

public class MutablePropertyDescriptorSupport extends PropertyEditorSupport{

	public MutablePropertyDescriptorSupport() {
	}

	@Override
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		super.addPropertyChangeListener(listener);
	}
	
	
	class DefaultPropertyChangeListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			String propertyName = event.getPropertyName();
			Object value = event.getNewValue();
			Object source = event.getSource();
			
			System.out.println(propertyName + value + source.toString());
		} 
		
	}
}
