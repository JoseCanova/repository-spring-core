package org.nanotek.proxy.map;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.assertj.core.util.introspection.ClassUtils;
import org.nanotek.Base;
import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.Artist_;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public class ProxyBuddy {

	private  List<Field> allClassFieldList;
	
	public Builder<?> buddy;
	
	public Class<?> powerClass;
	
	public Class<?> baseClass;
	

	public ProxyBuddy(Class<?> class1) {
		baseClass = class1;
		configureBaseBean();
	}

	public void configureBaseBean() {
		registryDynaBean();
	}

	@PostConstruct
	public void registryDynaBean() { 
		allClassFieldList = new ArrayList<>();
		List<Class<?>> intf =  getAllInterfaces(baseClass);
		System.out.println("Original Size"+intf.size());
		List<Field> fields = getFields(baseClass);
		buddy = new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(baseClass)
				.name("org.nanotek.proxy.buddy.BuddyProxy");
		fields.forEach(f->{buddy = buddy.defineProperty(f.getName(), f.getType());});
		intf.forEach(i->{buddy = buddy.implement(i);});
		
		powerClass = buddy.make()
				  .load(
						    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
						  .getLoaded();
	}
	
	public List<Class<?>> getAllInterfaces(Class<? extends Object> class1){ 
		return ClassUtils.getAllInterfaces(class1);
	}

	@SuppressWarnings("unchecked")
	public <K extends BaseEntity<?, ?>> BaseEntity<?, ?> createBaseEntity(Class<?> fieldClass) throws ClassNotFoundException{
		Class<? extends K> classK = (Class<? extends K>) Class.forName(fieldClass.getName()).asSubclass(BaseEntity.class);
		return Base.newInstance((Class<K>) fieldClass).get();
	}
	
	public List<Field> getFields(Class<?> classId) {
		return Arrays.asList(classId.getFields());
	}
	

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException { 
		ProxyBuddy a = new ProxyBuddy(Artist_.class);
		Class<?> ab =a.powerClass;
		
//		Class<?> ab =a.powerClass;
//		Artist<?> object = (Artist<?>) a.powerClass
//                .getDeclaredConstructor()
//                .newInstance();
//		Arrays.asList(object.getClass().getMethods()).forEach(m ->System.out.println(m.getName()));
//		Arrays.asList(ab.getFields()).forEach(f ->System.out.println(f.getName()));
//		object.setArtistId(12L);
//		System.out.println(object.getArtistId());
//		System.out.println(object.withUUID());
////		assertEquals(m.invoke(type.newInstance()), Bar.sayHelloBar());
////		a.allClassFieldList.forEach(f ->System.out.println(f.getName()));
//		System.out.println(ab.getName());
//		System.out.println("");
	}

}
