package org.nanotek;

import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;


public interface MutatorSupport<T> {

	
	default boolean instanceOf(Class<?> clazz) { 
		return Beans.isInstanceOf(this, clazz);
	}
	
	default void registerMutable(Class<?> clazz) { 
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] desc = beanInfo.getPropertyDescriptors();
			
//			Method[] ms = clazz.getDeclaredMethods();
//	        	for (PropertyDescriptor d : desc) {
//	        		System.out.println(d.getName());
//	        		System.out.println(d.getReadMethod());
//	        		System.out.println(d.getWriteMethod());
//	        	}
			}catch (Exception ex) { 
				ex.printStackTrace();
			}

	}
}
