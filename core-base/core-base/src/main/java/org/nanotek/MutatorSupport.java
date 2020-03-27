package org.nanotek;

import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;
import java.util.Optional;


public interface MutatorSupport<T> extends PropertyChangeSupportEntity<T>{


	default boolean instanceOf(Class<?> clazz) { 
		return Beans.isInstanceOf(this, clazz);
	}

	default void registerMutable(PropertyChangeListener listener) { 
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
			PropertyDescriptor[] desc = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor d : desc) {
				this.getPropertyChangeSupport().ifPresent(pcs ->{
					pcs.addPropertyChangeListener(d.getName(), listener);
				});
			}
		}catch (Exception ex) { 
			ex.printStackTrace();
		}
	}

	default  <V> V on(String property, V oldV , V v) {
		this.getPropertyChangeSupport().ifPresent(pcs ->{
			pcs.firePropertyChange(property, Optional.ofNullable(oldV).orElse(v), v);
		}); 
		return v;
	}

	static Optional<PropertyDescriptor> getPropertyDescriptor(Class<?> type) { 
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] desc = beanInfo.getPropertyDescriptors();
			if (desc.length > 1) return Optional.empty();
			return Optional.of(desc[0]);
		}catch (Exception ex) { 
			ex.printStackTrace();
		}
		return Optional.empty();
	}
}
