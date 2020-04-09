package org.nanotek.proxy.map;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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
import org.assertj.core.util.introspection.ClassUtils;
import org.nanotek.AttributeCopier;
import org.nanotek.MutatorSupport;
import org.nanotek.beans.entity.Artist_;

import com.google.common.base.Objects;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;

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
			 Object o =  powerClass.getConstructor().newInstance();
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
		
		Map<String,Class<?>> types = getAllInterfaces(entityClass);
		
		buddy = new ByteBuddy(ClassFileVersion.JAVA_V8)
				.subclass(clazz)
				.name("org.nanotek.proxy.buddy.BrainzMeta"+clazz.getSimpleName());
		buddy  = buddy.implement(AttributeCopier.class);
		getFields(metaModelClass).forEach(f->
							{
								Class<?> interf = types.get(f.getName());
								if(!f.getType().equals(String.class)&& interf!=null) {
//									buddy = buddy.defineProperty(f.getName(), f.getType());
									buddy = buddy.defineProperty(f.getName(), f.getType());
									//.implement(interf).intercept(FieldAccessor.ofBeanProperty())
//									TypeDescription.Generic td = TypeDescription 
//												.Generic.Builder.rawType(f.getType()).build();
//												parameterizedType(interf, f.getType()).build();
//									buddy = buddy.defineProperty(f.getName(),f.getType());
								}
							});
		powerClass =  buddy.make()
		  .load(
				    getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				  .getLoaded();
		return this;
	}

	public Map<String,Class<?>> getAllInterfaces(Class<? extends Object> class1){ 
		Type[] type = class1.getGenericInterfaces();
		ParameterizedType p = (ParameterizedType) type[0];
		Map<String,Class<?>> mapDescriptor = new HashMap<>();
		List<Type> theList = new ArrayList<>();
		 ClassUtils
		 	.getAllInterfaces((Class<?>) p.getRawType())
		 	.forEach(i->{
		 		i.getName().contains("Mutable");
//		 		for(Type t : i.getGenericSuperclass()) { 
		 		Optional<PropertyDescriptor> d = MutatorSupport.getPropertyDescriptor(i);
		 		d.ifPresent(p1->mapDescriptor.put(p1.getName(), i));
//		 			if(t instanceof ParameterizedType) {
//		 				ParameterizedType p = (ParameterizedType)t;
//		 				theList.add(p.getRawType());
//		 			}
//		 		}
		 	});;
		 
		 
		 return mapDescriptor;
	}
	
	public List<Field> getFields(Class<?> classId) {
		return Arrays.asList(classId.getFields());
	}
	
	
	public static <K> void main(String[] args) {
		BrainzMetaBuddy buddy =  BrainzMetaBuddy.with(Artist_.class);
		Object buddy1 = buddy.newInstance(); 
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
