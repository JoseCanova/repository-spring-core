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
extends ForwardBean<Map<String, PropertyDescriptorType>,B>
implements DataTransferMutator<String>
{
	private static final long serialVersionUID = 963493750343411962L;

	private B bean;

	@SuppressWarnings("unused")
	private  List<Field> allClassFieldList;

	private Map <String,PropertyDescriptorType> descriptorMap;

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
		PropertyDescriptorType pTye = descriptorMap.get(displayName);
		if(pTye ==null) { 
			descriptorMap.put(displayName, new PropertyDescriptorType(forMap));
		}else {
			descriptorMap.put(displayName, verifyValue(pTye,forMap));
		}
	}

	private PropertyDescriptorType verifyValue(PropertyDescriptorType v, PropertyDescriptor forMap) {
		return v.addPropertyDescriptor(forMap);
	}

	@Override
	public <V> V read(String t) {
		return (V) Optional.ofNullable(descriptorMap.get(t)).map(pd -> {
			try {
				return pd.getPropertyDescriptorRead().getReadMethod().invoke(bean, new Object[] {});
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
				pd.getPropertyDescriptorWrite().getWriteMethod().invoke(bean, new Object[] {v});
			} catch (Exception e) {
				e.printStackTrace();
				throw new BaseException(e);
			}
		});
	}


	@Override
	Map<String, PropertyDescriptorType> from() {
		return descriptorMap;
	}

	@Override
	public B to() {
		return bean;
	}

	public PropertyDescriptor toWhere(String propertyName){
		PropertyDescriptorType pa = from().get(propertyName);
		return Optional.ofNullable(pa).map(pt ->pt.getPropertyDescriptorWrite()).orElse(null);
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

enum TYPE{
	READ,
	WRITE;
}

class PropertyDescriptorType{


	private PropertyDescriptor propertyDescriptorRead;
	private PropertyDescriptor propertyDescriptorWrite;

	public PropertyDescriptorType(PropertyDescriptor old , PropertyDescriptor thenew) {
		if (old.getReadMethod()!=null)
			propertyDescriptorRead =old;
		if(old.getWriteMethod()!=null)
			propertyDescriptorWrite = old;
		if (old.getReadMethod()!=null)
			propertyDescriptorRead =thenew;
		if(old.getWriteMethod()!=null)
			propertyDescriptorWrite = thenew;
	}

	public PropertyDescriptorType(PropertyDescriptor propertyDescriptor) {
		if (propertyDescriptor.getReadMethod()!=null)
			propertyDescriptorRead =propertyDescriptor;
		if(propertyDescriptor.getWriteMethod()!=null)
			propertyDescriptorWrite = propertyDescriptor;		
	}

	public PropertyDescriptor getPropertyDescriptorRead() {
		return propertyDescriptorRead;
	}

	public PropertyDescriptor getPropertyDescriptorWrite() {
		return propertyDescriptorWrite;
	}

	public PropertyDescriptorType addPropertyDescriptor(PropertyDescriptor forMap) {
		if (forMap.getReadMethod()!=null)
			propertyDescriptorRead =forMap;
		if(forMap.getWriteMethod()!=null)
			propertyDescriptorWrite = forMap;
		return this;
	}

}


