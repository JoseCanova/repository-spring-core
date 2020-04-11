package org.nanotek.proxy.map;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.StaticMetamodel;

import org.nanotek.BaseException;
import org.nanotek.EntityTypeSupport;
import org.assertj.core.util.introspection.ClassUtils;
import org.nanotek.AttributeCopier;
import org.nanotek.MutatorSupport;
import org.nanotek.beans.entity.Artist_;

import com.google.common.base.Objects;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodCall.FieldSetting;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.jar.asm.Opcodes;

/**
 * Designed for MetaModel JPA Classes.
 * 
 * @author jose
 *
 */
public class BrainzMetaBuddy {

	
	public Builder<?> buddy;
	
	public Class<?> powerClass;
	
	public Class<?> metaModelClass;
	
	public Class<?> entityClass;

	private BrainzMetaBuddy(Class<?> class1) {
		metaModelClass = class1;
	}

	public static <K> BrainzMetaBuddy with(Class<? super K> class1) { 
		try {
				return new BrainzMetaBuddy(class1).configureBaseBean();
		}catch (Exception ex) {
			throw new BaseException(ex);
		}
	}
	
	public <K> K newInstance() {
		try {
			 return (K) powerClass.getConstructor().newInstance();
		}catch (Exception ex) {
			throw new BaseException(ex);
		}
	}
	
	public <K> K newInstance(EntityType<?> k) {
		try {
			 Constructor c = powerClass.getConstructor(EntityType.class);
			 Object o =  powerClass.getConstructor(EntityType.class).newInstance(new Object[] {k});
			 ((AttributeCopier)o).copy(k);
			 return (K)o;
		}catch (Exception ex) {
			throw new BaseException(ex);
		}
	}
	
	private BrainzMetaBuddy configureBaseBean() throws Exception {
		StaticMetamodel metaModel = metaModelClass.getAnnotation(StaticMetamodel.class);
		entityClass = Optional
								.ofNullable(metaModel)
								.map(m->m.value())
								.orElseThrow(BaseException::new);
		Class<?> clazz = metaModelClass.asSubclass(metaModelClass);
		
		buddy = new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(clazz,ConstructorStrategy.Default.DEFAULT_CONSTRUCTOR)
				.name("org.nanotek.proxy.buddy.BrainzMeta"+clazz.getSimpleName());
		buddy  = buddy.implement(AttributeCopier.class);
		buddy  = buddy.implement(EntityTypeSupport.class);
		buddy = buddy.defineProperty("entityType", EntityType.class);
		buddy  = buddy.defineConstructor(Visibility.PUBLIC )
				.withParameter(EntityType.class,"entityType",Opcodes.ACC_MANDATED)
				.intercept(MethodCall
			               .invoke(metaModelClass.getDeclaredConstructor())
			               .onSuper()
			               .andThen(FieldAccessor.ofField("entityType").setsArgumentAt(0)));
				getFields(metaModelClass).forEach(f->
							{   
								if(!f.getType().equals(String.class)) {
									buddy = buddy.defineProperty(f.getName(), f.getType());
								}
							});
		powerClass =  buddy.make()
		  .load(
				    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				  .getLoaded();
		return this;
	}

	public List<Field> getFields(Class<?> classId) {
		return Arrays.asList(classId.getFields());
	}
	
	public static <K> void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
//		Class<?> target = new ByteBuddy()
//				  .subclass(Object.class)
//				  .name("com.test.MyClass")
//				  .defineConstructor(Opcodes.ACC_PUBLIC)
//				  .withParameters(String.class)
//				  .intercept(MethodCall.invoke(Object.class.getConstructor()).andThen(FieldAccessor.ofField("hello").setsArgumentAt(0)))
//				  .defineField("hello", String.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL).value("world")
//				  .make()
//				  .load(ClassLoader.getSystemClassLoader()).getLoaded();
//
//				  Object targetObj = target.getConstructor(String.class).newInstance("world");
//
//				  Field f = target.getDeclaredField("hello");
//				  f.setAccessible(true);
//				  System.out.println(f.get(targetObj));
		
		
		BrainzMetaBuddy buddy =  BrainzMetaBuddy.with(Artist_.class);
		Object buddy1 = buddy.newInstance(null); 
		MutatorSupport
		.getPropertyDescriptors(buddy1.getClass())
		.ifPresent(properties->{
			Stream
			.of(properties)
			.forEach(p->{
				System.out.println(p.getReadMethod());
				System.out.println(p.getWriteMethod());
			});
		});
	}

}
