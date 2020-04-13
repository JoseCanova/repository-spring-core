package org.nanotek;

import java.beans.Beans;
//import java.beans.BeanInfo;
//import java.beans.Beans;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

import org.nanotek.beans.BeanInfo;
import org.nanotek.beans.Introspector;
import org.nanotek.beans.PropertyDescriptor;


public interface MutatorSupport<T> {


	default boolean instanceOf(Class<?> clazz) { 
		return Beans.isInstanceOf(this, clazz);
	}
	
    public static boolean isInstanceOf(Object bean, Class<?> targetType) {
        return Introspector.isSubclass(bean.getClass(), targetType);
    }

	default <Z> Optional<? super Z> getProperty(String propertyName) { 
		return getPropertyDescriptors(this.getClass())
		.map(ps->{
			PropertyDescriptor z = null;
			return Stream
				.of(ps)
				.reduce(z,(test , value)->{
					if(value.getName().equals(propertyName)) {
						test = value;
					}
					return test;
				});
		})
		.map(p->read(p.getReadMethod(), this));
	}
	
	default Object read(Method readMethod, MutatorSupport<T> mutatorSupport) {
		try{
			return readMethod.invoke(mutatorSupport, new Object[] {});
		}catch(Exception ex) { 
			throw new BaseException();
		}
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
