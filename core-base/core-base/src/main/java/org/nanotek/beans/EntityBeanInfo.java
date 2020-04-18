package org.nanotek.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.nanotek.BaseException;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.sun.introspect.ClassInfo;

public class EntityBeanInfo<E> extends ClassInfo {

	private Class<E> entityClass;
	
	private static 
		Map<Class<?> , Map<String,PropertyDescriptor>> classDescriptorMap = 
										new HashMap<Class<?> , Map<String,PropertyDescriptor>>();
	
	private Map<String,PropertyDescriptor> propertyDescriptorInfo;
	
    private static Semaphore semaphore = new Semaphore(1);
	
	private  AtomicBoolean isConfigured = new AtomicBoolean(false);
	
	public boolean  isConfigured() {
			acquire();
			if((propertyDescriptorInfo =
						classDescriptorMap.get(this.entityClass))!=null) {
				isConfigured.set(true);
			}
			release();
		return isConfigured.get();
	}
	
	public Class<E> getEntityClass() {
		return entityClass;
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
			prepareChache(entityClass)
				.construcMethodInfoList()
				.contructPropertiesInfoList()
				.constructProperyDescriptorInfos();
			classDescriptorMap.put(entityClass, propertyDescriptorInfo);
			setConfigured(true);
		}
	}
	
	private void constructProperyDescriptorInfos() {
		try {
			BeanInfo binfo = Introspector.getBeanInfo(getEntityClass());
			Optional
						.ofNullable(binfo.getPropertyDescriptors())
						.ifPresent(ps -> {
							Stream
								.of(ps)
								.forEach(p->propertyDescriptorInfo.put(p.getName(), p));
						});
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BaseException(e1);
		}
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
		for (int i = 0 ; i < 100 ; i++) { 
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("started thread " + Thread.currentThread().getName());
					EntityBeanInfo<?> entityBeanInfo = new EntityBeanInfo<>(ArtistBean.class);
					entityBeanInfo
						.getPropertyDescriptorInfo()
						.forEach((k,v)-> System.out.println(v.getName() + "  " + Thread.currentThread().getName()));
				} 
				
			});
			t.start();
		}
	}
}
