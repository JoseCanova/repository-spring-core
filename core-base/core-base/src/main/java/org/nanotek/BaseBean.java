package org.nanotek;

import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.assertj.core.util.introspection.ClassUtils;
import org.nanotek.opencsv.CsvBaseBean;

public  interface BaseBean<K extends ImmutableBase<K,ID> , ID extends IdBase<?,?>> extends ImmutableBase<K,ID> , MutatorSupport<K>
{

	public static enum METHOD_TYPE { 
		READ,
		WRITE;
	}


	boolean registryMethod(Class<?> classId, Class<?> i, String name, Method writeMethod, METHOD_TYPE write);
	
	CsvBaseBean<?,?> getReference();
	
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
				map(p -> getReference().readB(clazz).get());
	}


	default <V>  Optional<?> read(Class<?> ownerClass, Class<V> clazz){ 
		return MutatorSupport
				.getPropertyDescriptor(clazz).filter(pd-> pd!=null).
				map(p -> getReference().readB(ownerClass,clazz).get());
	}
	
	@PostConstruct
	default void registryDynaBean() { 
		mountInstanceMap();
		List<Class<?>> intf =  getAllInterfaces(getId().getClass());
		System.out.println("Original Size"+intf.size());
		List<Class<?>> remainingInterfaces = processInterfaces(getBaseClass(),intf);
		processRemainingInterfaces(getId().getClass(),remainingInterfaces);
	}
	
	default void mountInstanceMap() {
		Class<? extends ID> baseEntity = getBaseClass();
		Arrays.asList(baseEntity.getFields())
					.stream()
					.forEach(f -> {
						try { 
							if (f.get(getId()) == null) { 
								Class<?> fieldClass = f.getType();
								BaseEntity<?,?> entity = createBaseEntity(fieldClass);
								f.set(getId(), baseEntity);
								getInstanceMap().put(f.getType(), entity);
							}else {
								getInstanceMap().put(f.getType(), BaseEntity.class.cast(f.get(getId())));
							}
						}catch  (Exception ex) { 
							throw new BaseException(ex);
						}
					});
	}

	@SuppressWarnings("unchecked")
	default <K extends BaseEntity<?, ?>> BaseEntity<?, ?> createBaseEntity(Class<?> fieldClass) throws ClassNotFoundException{
		Class<? extends K> classK = (Class<? extends K>) Class.forName(fieldClass.getName()).asSubclass(BaseEntity.class);
		return Base.newInstance((Class<K>) fieldClass).get();
	}

	default void processRemainingInterfaces(Class<?> classId, List<Class<?>> remainingInterfaces) {
		List<Class<?>> filtered =  Optional.ofNullable( remainingInterfaces).orElse(new ArrayList<>()).stream()
										.collect(Collectors.toList());
		List<Class<?>> notRegistred = null;
		System.out.println("Remaining " + filtered.toString());
		if (classId == null )return;
		Field[] fields = classId.getFields();
		Class<?> newClassID = null;
		for (Field f : fields) {
			 if (f.getType().getPackageName().contains("org.nanotek.beans.entity") && !f.getType().equals(getId().getClass()))
				 { try {
					List <Class<?>> typeInterfaces = getAllInterfaces(f.getType());
					newClassID = f.getType();
					System.out.println(typeInterfaces);
					filtered.addAll(typeInterfaces);
					notRegistred = processInterfaces(newClassID, filtered);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
		}
		processRemainingInterfaces(newClassID,notRegistred);
	}
	
	
	
	default List<Class<?>> getAllInterfaces(Class<? extends Object> class1){ 
		return ClassUtils.getAllInterfaces(class1);
	}


	default List<Class<?>> processInterfaces(Class<?> classId , List<Class<?>> allInterfaces) {
		return allInterfaces.stream()
				.filter(interf -> registredInterface(classId , interf) == false)
				.collect(Collectors.toList());
	}

	default boolean registredInterface(Class<?> classId  , Class<?> interf) {
		Optional<PropertyDescriptor> pd = MutatorSupport.getPropertyDescriptor(interf);
		return	pd.map(p-> {
			boolean result = false;
			if(p.getWriteMethod() !=null) { 
				result = registryMethod(classId , interf , p.getName() , p.getWriteMethod(),METHOD_TYPE.WRITE);
				System.out.println(result + " " +  classId.toString() + " " + interf.toString());
			}
			if(p.getReadMethod() !=null) {
				result = registryMethod(classId , interf , p.getName() , p.getReadMethod(),METHOD_TYPE.READ);
			}
			return result;
		}).filter(r -> r==true).orElse(false);

	}

	class ClassHandle<T>{
		
		Class<T> clazz;
		
		MethodHandle methodHandle;
		
		
		public ClassHandle(Class<T> clazz, MethodHandle methodHandle) {
			super();
			this.clazz = clazz;
			this.methodHandle = methodHandle;
		}

		public Class<T> getClazz() {
			return clazz;
		}

		public MethodHandle getMethodHandle() {
			return methodHandle;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
			result = prime * result + ((methodHandle == null) ? 0 : methodHandle.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ClassHandle other = (ClassHandle) obj;
			if (clazz == null) {
				if (other.clazz != null)
					return false;
			} else if (!clazz.equals(other.clazz))
				return false;
			if (methodHandle == null) {
				if (other.methodHandle != null)
					return false;
			} else if (!methodHandle.equals(other.methodHandle))
				return false;
			return true;
		}


	}
	
	
	
	
	
}
