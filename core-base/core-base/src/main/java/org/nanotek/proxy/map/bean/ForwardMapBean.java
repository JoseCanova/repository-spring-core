package org.nanotek.proxy.map.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseException;
import org.nanotek.DataTransferMutator;
import org.nanotek.MutatorSupport;
import org.nanotek.beans.PropertyDescriptor;
import org.nanotek.beans.entity.Area;
import org.springframework.util.ClassUtils;

@SuppressWarnings("unchecked")
public class ForwardMapBean<B extends Base<?>>
extends ForwardBean<Map<String, PropertyDescriptor>,B>
implements DataTransferMutator<String>
{
	private static final long serialVersionUID = 963493750343411962L;

	private B bean;

	@SuppressWarnings("unused")
	private  List<Field> allClassFieldList;

	private Map <String,PropertyDescriptor> descriptorMap;

	public Class<?> powerClass;

	public Class<?> baseClass;


	public ForwardMapBean() {
	}

	public ForwardMapBean(Class<?> clazz) {
		baseClass = clazz;
		postConstruct(clazz , null);
	}

	public ForwardMapBean(Class<?> clazz , B b) {
		baseClass = clazz;
		bean = b;
		postConstruct(clazz , b);
	}

	private void postConstruct(Class<?> clazz,B b) {
		descriptorMap = new HashMap<>();
		populatePropertyDescriptorMap(clazz);
		try {
			if(b == null)
				bean = (B) clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e);
		}
	}

	private void populatePropertyDescriptorMap(Class<?> clazz) {
		Optional <PropertyDescriptor[]> pd = MutatorSupport.getPropertyDescriptors(clazz);
		Arrays.asList(pd.get()).forEach(property->{
			if (property.getReadMethod() !=null || property.getWriteMethod()!=null)
				addDescriptorOnMap(property.getDisplayName(),property);
		});
	}

	private void addDescriptorOnMap(String displayName, PropertyDescriptor forMap) {
			descriptorMap.put(displayName, forMap);
	}


	@Override
	public <V> V read(String t) {
		return (V) Optional.ofNullable(descriptorMap.get(t)).map(pd -> {
			try {
				return pd.getReadMethod() !=null?pd.getReadMethod().invoke(bean, new Object[] {}):null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new BaseException(e);
			}
		});
	}

	@Override
	public <V> void write(String t, V v) {
		Optional.ofNullable(descriptorMap.get(t)).ifPresent(pd -> {
			try {
				 if (pd.getWriteMethod()!=null)
					 	pd.getWriteMethod().invoke(bean, new Object[] {v});
			} catch (Exception e) {
				e.printStackTrace();
				throw new BaseException(e);
			}
		});
	}


	@Override
	public Map<String, PropertyDescriptor> from() {
		return descriptorMap;
	}

	@Override
	public B to() {
		return bean;
	}

	public PropertyDescriptor toWhere(String propertyName){
		PropertyDescriptor pa = from().get(propertyName);
		return Optional.ofNullable(pa).map(pt ->pt).orElse(null);
	}


	public List<Class<?>> getAllInterfaces(Class<? extends Object> class1){ 
		return Arrays.asList(ClassUtils.getAllInterfaces(class1));
	}

	public List<Field> getFields(Class<?> classId) {
		return Arrays.asList(classId.getFields());
	}



	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException { 

		ForwardMapBean<?> dm = new ForwardMapBean<>(Area.class);
		PropertyDescriptor pd= dm.toWhere("areaName");
		System.out.println(pd!=null?pd.getName():"no where");
		dm.write("areaName", "The Name");
		System.out.println("");
		Object vv = dm.read("areaName");
		System.out.println(vv);
	}

}


