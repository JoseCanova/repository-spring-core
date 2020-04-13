package org.nanotek.beans;

import java.util.HashMap;
import java.util.Map;

import org.nanotek.IdBase;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.sun.introspect.ClassInfo;

public class EntityBeanInfo<E extends IdBase<?,?>> extends ClassInfo {

	
	private Class<E> entityClass;
	
	private Map<String,PropertyDescriptor> propertyDescriptorInfo;
	
	public EntityBeanInfo(Class<E> entityClass) {
		super(entityClass);
		this.entityClass = entityClass;
		postContruct();
	}

	EntityBeanInfo <?> construcMethodInfoList(){
		this.methods = ClassInfo.get(entityClass).getMethods();
		return this;
	}
	
	private void postContruct() {
		propertyDescriptorInfo = new HashMap<String,PropertyDescriptor>();
		prepareChache(entityClass).construcMethodInfoList().contructPropertiesInfoList().constructProperyDescriptorInfos();
	}
	
	private void constructProperyDescriptorInfos() {
		properties.forEach((k,v) -> {
			try {
				String normalized = toPropertyName(k);
				PropertyDescriptor pd = new PropertyDescriptor(normalized , v.getReadMethod() , v.getWriteMethod());
				propertyDescriptorInfo.put(normalized, pd);
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} 
		});
	}

	private String toPropertyName(String k) {
		return new StringBuilder().append(k.substring(0, 1).toLowerCase()).append(k.substring(1, k.length())).toString();
	}

	private EntityBeanInfo <?> contructPropertiesInfoList() {
		this.properties = ClassInfo.get(entityClass).getProperties();
		return this;
	}

	public   EntityBeanInfo <?>  prepareChache(Class<?> type) {
		ClassInfo.get(type);
		return this;
	}
	
	public Map<String, PropertyDescriptor> getPropertyDescriptorInfo() {
		return propertyDescriptorInfo;
	}
	
	public static void main(String[] args) {
		EntityBeanInfo<?> entityBeanInfo = new EntityBeanInfo<>(Artist.class);
		System.out.println("'");
		EntityBeanInfo<?> baseBeanInfo = new EntityBeanInfo<>(ArtistBean.class);
		System.out.println("'");
	}
}
