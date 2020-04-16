package org.nanotek.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import org.nanotek.BaseException;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.sun.introspect.ClassInfo;

public class EntityBeanInfo<E> extends ClassInfo {

	private Class<E> entityClass;
	
	private static 
		Map<Class<?> , Map<String,PropertyDescriptor>> classDescriptorMap = 
										new HashMap<Class<?> , Map<String,PropertyDescriptor>>();
	
	private Map<String,PropertyDescriptor> propertyDescriptorInfo;
	
    private static Semaphore semaphore = new Semaphore(1);
	
	private static AtomicBoolean isConfigured = new AtomicBoolean(false);
	
	public boolean  isConfigured() {
			acquire();
			if(classDescriptorMap.get(this.entityClass)!=null) {
				isConfigured.set(true);
			}
			release();
		return isConfigured.get();
	}
	
	public void setConfigured(boolean b) {
		isConfigured.set(b);
		release();
	}
	
	public void acquire() {
		try { 
			semaphore.acquire();
		}catch (Exception ex) { 
			isConfigured.set(false);
			throw new BaseException(ex);
		}
	}
	
	public void release() { 
		try { 
			semaphore.release();
		}catch (Exception ex) { 
			throw new BaseException(ex);
		}
	}
	
	
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
		if(!isConfigured()) { 
			acquire();
			propertyDescriptorInfo = new HashMap<String,PropertyDescriptor>();
			prepareChache(entityClass).construcMethodInfoList().contructPropertiesInfoList().constructProperyDescriptorInfos();
			classDescriptorMap.put(entityClass, propertyDescriptorInfo);
			setConfigured(true);
		}else { 
			System.out.println("Already Configured " + Thread.currentThread().getName());
			propertyDescriptorInfo = classDescriptorMap.get(entityClass);
		}
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
		for (int i = 0 ; i < 100 ; i++) { 
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("started thread " + Thread.currentThread().getName());
					EntityBeanInfo<?> entityBeanInfo = new EntityBeanInfo<>(Artist.class);
					entityBeanInfo
						.getPropertyDescriptorInfo()
						.forEach((k,v)-> System.out.println(v.getName() + "  " + Thread.currentThread().getName()));
				} 
				
			});
			t.start();
		}
	}
}
