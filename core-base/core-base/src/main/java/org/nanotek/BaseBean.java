package org.nanotek;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.assertj.core.util.introspection.ClassUtils;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.opencsv.CsvBaseBean;

public  interface BaseBean<K extends ImmutableBase<K,ID> , ID extends IdBase<?,?>> extends ImmutableBase<K,ID> , MutatorSupport<K>
{

	
	public static enum METHOD_TYPE { 
		READ,
		WRITE;
	}
	
	
	boolean registryMethod(Class<?> i, String name, Method writeMethod, METHOD_TYPE write);
	CsvBaseBean<?> getCsvBaseBean();
	
	static <K extends ImmutableBase> Class<? extends K> 
	prepareBaseBeanClass(Class<K> clazz) {
		try {
			return (Class<? extends K>) Class.forName(clazz.getName()).asSubclass(ImmutableBase.class);
		}catch(Exception ex) {throw new BaseException();}
	}
	
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
					.ifPresent(m -> getCsvBaseBean().writeA(clazz,vl)));
		return Optional.ofNullable(v);
	}
	
	default <V>  Optional<?> read(Class<V> clazz){ 
		return MutatorSupport
					.getPropertyDescriptor(clazz).filter(pd-> pd!=null).
						map(p -> getCsvBaseBean().readB(clazz).get());
	}
	
	@PostConstruct
	default void registryDynaBean() { 
		List<Class<?>> intf =  ClassUtils.getAllInterfaces(getId().getClass());
		processInterfaces(intf);
	}
	
	
	default void processInterfaces(List<Class<?>> allInterfaces) {
		List<Class<?>> remaining = allInterfaces.stream()
					.filter(interf -> registredInterface(interf) == true).collect(Collectors.toList());
		System.out.println("LIST OF REMAINING CLASSES " + remaining.size());
	}
	
	default boolean registredInterface(Class<?> interf) {
		Optional<PropertyDescriptor> pd = MutatorSupport.getPropertyDescriptor(interf);
	return	pd.map(p-> {
			boolean result = false;
			if(p.getWriteMethod() !=null) { 
				result = registryMethod(interf , p.getName() , p.getWriteMethod(),METHOD_TYPE.WRITE);
			}
			if(p.getReadMethod() !=null) {
				result = registryMethod(interf , p.getName() , p.getReadMethod(),METHOD_TYPE.READ);
			}
			return result;
		}).filter(r -> r==true).orElse(false);
		
//		pd.ifPresent(p -> {
//			boolean result = false;
//			if(p.getWriteMethod() !=null) { 
//				result = registryMethod(interf , p.getName() , p.getWriteMethod(),METHOD_TYPE.WRITE);
//			}
//			if(p.getReadMethod() !=null) {
//				result = registryMethod(interf , p.getName() , p.getReadMethod(),METHOD_TYPE.READ);
//			}
//		});
	}

}
