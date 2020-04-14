package org.nanotek;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

import org.nanotek.beans.BeanInfo;
import org.nanotek.beans.Introspector;
import org.nanotek.beans.PropertyDescriptor;


public interface MutatorSupport<T> {


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

	//TODO: define a criteria verify parameters from readmethod
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


	/**
	 * Check if class is a property class (ie mutator acessor class).
	 * 
	 * @param type
	 * @return
	 */
	static boolean isPropertyBean(Class<?> clazz) {
		try {	
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] desc = beanInfo.getPropertyDescriptors();
			if(desc == null || desc.length !=1) return false; 
			PropertyDescriptor descr=desc[0];
			return descr.getWriteMethod() !=null || descr.getReadMethod() !=null;
		}catch (Exception ex) { 
			ex.printStackTrace();
		}
		return false;
	}

}
