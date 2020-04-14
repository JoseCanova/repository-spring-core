package org.nanotek;

import org.nanotek.beans.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.assertj.core.util.introspection.ClassUtils;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.proxy.ProxyBase;

public  interface BaseBean<K extends ImmutableBase<K,ID> , ID extends IdBase<?,?>> extends ImmutableBase<K,ID> , MutatorSupport<K>,Configurable<ID>
{

	public static enum METHOD_TYPE { 
		READ,
		WRITE;
	}

	default boolean  isConfigured() {
		return true;
	}
	
	default void setConfigured(boolean b) {
	}

	boolean registryMethod(Class<?> classId, Class<?> i, String name, Method writeMethod, METHOD_TYPE write);
	
	ProxyBase<?,?> getReference();
	
	Class<? extends ID> getBaseClass ();
	
	HashMap<Class<?> , BaseEntity<?,?>> getInstanceMap();

	static <K extends ImmutableBase> Class<? extends K> 
	prepareBaseBeanClass(Class<K> clazz) {
		try {
			return (Class<? extends K>) Class.forName(clazz.getName()).asSubclass(ImmutableBase.class);
		}catch(Exception ex) {throw new BaseException();}
	}

	static <K extends BaseBean<?,?>> Optional<K> 
	newBaseBeanInstance(Class<K> clazz) throws BaseInstantiationException { 
		try {
			return Optional.of(clazz.getDeclaredConstructor().newInstance());
		} catch (Exception e) {
			throw new BaseInstantiationException(e);
		}
	}

	static <K extends BaseBean<?,?>, S extends K> 
	Optional<S> newBaseBeanInstance(Class<S> clazz , Object[] args , Class<?>... classArgs  ) 
			throws BaseInstantiationException { 
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
		.ifPresent(m -> getReference().writeA(clazz,vl)));
		return Optional.ofNullable(v);
	}
	
	default <V>  Optional<V> write(Class<?> ownerClass , Class<?> clazz , V v){ 
		Optional.ofNullable(v).ifPresent(vl ->
		MutatorSupport
		.getPropertyDescriptor(clazz)
		.ifPresent(m -> getReference().writeA(ownerClass,clazz,vl)));
		return Optional.ofNullable(v);
	}

	default <V>  Optional<?> read(Class<V> clazz){ 
		return MutatorSupport
				.getPropertyDescriptor(clazz).filter(pd-> pd!=null).
				map(p -> getReference().readB(clazz).orElse(null));
	}


	default <V>  Optional<?> read(Class<?> ownerClass, Class<V> clazz){ 
		return MutatorSupport
				.getPropertyDescriptor(clazz).filter(pd-> pd!=null).
				map(p -> getReference().readB(ownerClass,clazz).orElse(null));
	}
	
	@PostConstruct
	default void registryDynaBean() { 
		List<Class<?>> intf =  getAllInterfaces(getId().getClass());
		System.out.println("Original Size"+intf.size());
		registryProperties(getBaseClass(),intf);
		processAttributesProperties(getId().getClass());
	}
	
	void mountInstanceMap();

	@SuppressWarnings("unchecked")
	default <K extends BaseEntity<?, ?>> BaseEntity<?, ?> createBaseEntity(Class<?> fieldClass) throws ClassNotFoundException{
		Class<? extends K> classK = (Class<? extends K>) Class.forName(fieldClass.getName()).asSubclass(BaseEntity.class);
		return Base.newInstance((Class<K>) fieldClass).get();
	}

	//TODO:Check the original idea of this.
	default void processAttributesProperties(Class<?> classId) {
//		System.out.println("Remaining " + filtered.toString());
		Field[] fields = classId.getFields();
		Class<?> newClassID = null;
		for (Field f : fields) {
			 if (f.getType().getPackageName().contains("org.nanotek.beans.entity") && !f.getType().equals(getId().getClass()))
				 { try {
					List <Class<?>> typeInterfaces = getAllInterfaces(f.getType());
					newClassID = f.getType();
					registryProperties(newClassID,typeInterfaces);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
		}
		setConfigured(true);
		return;
	}
	
	default List<Class<?>> getAllInterfaces(Class<? extends Object> class1){ 
		List<Class<?>> interfaces = new ArrayList<>();
		Class<?> [] intAry = class1.getInterfaces();
		if(intAry.length > 0) { 
			interfaces.addAll(Arrays.asList(intAry));
			for(Class<?> c:intAry)
				interfaces.addAll(getAllInterfaces(c));
		}
		return interfaces;
	}


	default List<Class<?>> registryProperties(Class<?> classId , List<Class<?>> allInterfaces) {
		return allInterfaces.stream()
				.filter(interf -> !registredInterface(classId , interf))
				.collect(Collectors.toList());
	}

	default boolean registredInterface(Class<?> classId  , Class<?> interf) {
		Optional<PropertyDescriptor> pd = MutatorSupport.getPropertyDescriptor(interf);
		return	pd.map(p-> {
			boolean result = false;
			if(p.getWriteMethod() !=null) { 
				result = registryMethod(classId , p.getWriteMethod().getDeclaringClass() , p.getName() , p.getWriteMethod(),METHOD_TYPE.WRITE);
			}
			if(p.getReadMethod() !=null) {
				result = registryMethod(classId , p.getReadMethod().getDeclaringClass() , p.getName() , p.getReadMethod(),METHOD_TYPE.READ);
			}
//			TODO:Puta a debug line here
//			System.out.println(result + " " +  classId.toString() + " " + interf.toString());
			return result;
		}).orElse(false);

	}

	class ClassHandle<T>{
		
		Class<T> clazz;
		
		MethodHandle methodHandle;
		
		METHOD_TYPE methodTypeEnum; 
		
		MethodType methodType;
		
		public ClassHandle(Class<T> clazz, MethodHandle methodHandle) {
			super();
			this.clazz = clazz;
			this.methodHandle = methodHandle;
		}

		public ClassHandle(Class<T> clazz, MethodHandle methodHandle, METHOD_TYPE methodType) {
			super();
			this.clazz = clazz;
			this.methodHandle = methodHandle;
			this.methodTypeEnum = methodType;
		}

		
		
		public ClassHandle(Class<T> clazz, MethodHandle methodHandle, METHOD_TYPE methodTypeEnum,
				MethodType methodType) {
			super();
			this.clazz = clazz;
			this.methodHandle = methodHandle;
			this.methodTypeEnum = methodTypeEnum;
			this.methodType = methodType;
		}

		public Class<T> getClazz() {
			return clazz;
		}

		public MethodHandle getMethodHandle() {
			return methodHandle;
		}

		public METHOD_TYPE getMethodTypeEnum() {
			return methodTypeEnum;
		}

		public MethodType getMethodType() {
			return methodType;
		}

	}
	
	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				ArtistBean<?> artistBean = new ArtistBean<>();
			}
			
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				ArtistBean<?> artistBean = new ArtistBean<>();
			}
			
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				ArtistBean<?> artistBean = new ArtistBean<>();
			}
			
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				ArtistBean<?> artistBean = new ArtistBean<>();
			}
			
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				ArtistBean<?> artistBean = new ArtistBean<>();
			}
			
		}).start();
	}
	
}