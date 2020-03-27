package org.nanotek;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.assertj.core.util.introspection.ClassUtils;
import org.nanotek.opencsv.CsvBaseBean;

public  interface BaseBean<K extends ImmutableBase<K,ID> , ID extends IdBase<?,?>> extends ImmutableBase<K,ID> , MutatorSupport<K>
{

	String getAtributeName(Class<?> clazz);
	
	void registryMethod(Class<?> clazz, String atributeName);

	CsvBaseBean<?> getCsvBaseBean();
	
	static <K extends BaseBean<?,?>> Optional<K> newBaseBeanInstance(Class<K> clazz) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor().newInstance());
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
	static <K extends BaseBean<?,?>, S extends K> Optional<S> newBaseBeanInstance(Class<S> clazz , Object[] args , Class<?>... classArgs  ) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor(classArgs).newInstance(args));
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}
	
	default <V>  Optional<V> write(Class<?> clazz , V v){ 
		Optional.ofNullable(v).ifPresent(vl ->
					MutatorSupport
					.getPropertyDescriptor(clazz)
					.ifPresent(m -> getCsvBaseBean().set(m.getName(),vl)));
		return Optional.ofNullable(v);
	}
	
	default <V>  Optional<?> read(Class<V> clazz){ 
		return MutatorSupport
					.getPropertyDescriptor(clazz).filter(pd-> pd!=null).
						map(p -> getCsvBaseBean().get(p.getName()));
	}
	
	@PostConstruct
	default void registryDynaBean() { 
		List<Class<?>> intf =  ClassUtils.getAllInterfaces(this.getClass());
		for (Class<?> i : intf) {
			Optional<PropertyDescriptor> pd = MutatorSupport.getPropertyDescriptor(i);
			pd.ifPresent(p -> {
				if(p.getWriteMethod() !=null) { 
					registryMethod(i , p.getName());
				}
				if(p.getReadMethod() !=null) {
					registryMethod(i , p.getName());
				}
			});
		}
	}
	
	
}
