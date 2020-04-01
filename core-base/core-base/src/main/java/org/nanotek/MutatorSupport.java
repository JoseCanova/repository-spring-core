package org.nanotek;

import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Optional;


public interface MutatorSupport<T> {


	default boolean instanceOf(Class<?> clazz) { 
		return Beans.isInstanceOf(this, clazz);
	}

	static Optional<PropertyDescriptor[]> getPropertyDescriptors(Class<?> type) { 
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] desc = beanInfo.getPropertyDescriptors();
			return Optional.of(desc);
		}catch (Exception ex) { 
			ex.printStackTrace();
		}
		return Optional.empty();
	}
	/**
	 * use just for acessor/mutator interfaces.
	 * @param type
	 * @return
	 */
	static Optional<PropertyDescriptor> getPropertyDescriptor(Class<?> type) { 
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] desc = beanInfo.getPropertyDescriptors();
			if (desc ==null || desc.length != 1) return Optional.empty();
			return Optional.of(desc[0]);
		}catch (Exception ex) { 
			ex.printStackTrace();
		}
		return Optional.empty();
	}
}
