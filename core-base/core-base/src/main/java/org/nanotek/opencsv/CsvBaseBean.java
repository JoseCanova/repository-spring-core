package org.nanotek.opencsv;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseBean.METHOD_TYPE;
import org.nanotek.IdBase;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Artist;

public class CsvBaseBean<ID extends Base<?>> 
{

	private static final long serialVersionUID = -1465843449151457466L;
	
	private MethodHandles.Lookup lookup = MethodHandles.lookup();

	private ID id;

	protected Class<? extends ID> baseClass;

	protected PropertyChangeSupport propertyChangeSupport = null;

	private Map<Class<?> , MethodHandle> interfaceMap;

//	public CsvBaseBean() {
//		super(prepareBeanInstance(CsvBaseBean.class));
//		baseClass = (Class<ID>) this.getClass();
//		id =  baseClass.cast(getInstance());
//		interfaceMap = new HashMap<>();
//	}

	public CsvBaseBean(Class<? extends ID> class1) {
//		super(CsvBaseBean.prepareBeanInstance(class1.asSubclass(IdBase.class)));
		baseClass = class1;
		id =  class1.cast(CsvBaseBean.prepareBeanInstance(class1.asSubclass(IdBase.class)));
		interfaceMap = new HashMap<>();
	}


	@SuppressWarnings("unchecked")
	private static IdBase<?,?> prepareBeanInstance(Class<?> idBase) { 
		return IdBase.prepareBeanInstance(idBase.asSubclass(IdBase.class));
	}


	public Optional<PropertyChangeSupport> getPropertyChangeSupport() {
		return Optional.ofNullable(propertyChangeSupport);
	}

	public MethodHandle getAtributeNameInstance(Class<?> clazz) {
		return interfaceMap.get(clazz);
	}

	public void registryMethod(Class<?> clazz, String atributeName, Method method , BaseBean.METHOD_TYPE mtype) {
		Arrays.asList(baseClass.getFields()).forEach(f ->{
			boolean found=false;
			System.out.println(f.getName().equals(atributeName) + "  " + clazz.getName());
			if(f.getName().equals(atributeName)){
				found = true;
				Class<?>[] parameters = method.getParameterTypes();
				Class<?> returnType  = method.getReturnType();
				MethodType mt = MethodType.methodType(returnType, mtype.equals(METHOD_TYPE.WRITE)?parameters:new Class[] {});
				MethodHandle mh;
				try {
					mh = lookup.findVirtual(this.getClass(), method.getName(), mt);
					mh.bindTo(this.getId());
					interfaceMap.put(clazz, mh);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(clazz.getName() + "  " + atributeName + "  " + method.getName());
			}
//			else { 
//				Object objectRef = findObjectInstance(this,clazz, atributeName, method , mtype);
//			}
//			else { 
//				if(!f.getType().isPrimitive()) { 
//					for (Field fi : f.getType().getFields()) {
//						registryMethod(clazz, atributeName, method);
//					}
//				}
//			}
		});
	}

	


	private Object findObjectInstance(CsvBaseBean<ID> csvBaseBean, Class<?> clazz, String atributeName, Method method,
			METHOD_TYPE mtype) {
		return null;
	}


	public <V> Optional<V> writeA(Class<?> clazz , V v){
		return Optional.ofNullable(interfaceMap.get(clazz)).map(f -> 
		{
			try {
				f.invoke(this.getId(),v);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return v;});
	}

	public Optional<?> readB(Class<?> clazz){
		return Optional.ofNullable(interfaceMap.get(clazz)).map(f -> 
		{
			try {
				return f.invoke(this.getId());
			}catch(Throwable ex) {
				ex.printStackTrace();
			}
			return null;
			
		});
	}
	public CsvBaseBean<?> getCsvBaseBean(){
		return this;
	}

	public static void main(String[] args) { 
		ArtistBean<?> a = new ArtistBean(Artist.class);

		Class<?>[] c = Artist.class.getDeclaredClasses();
		a.registryDynaBean();
		a.setArtistId(1000L);
		a.getArtistId();
//		a.setBeginDateYear(10);
//		a.setBeginDateMonth(10);
		System.out.println(a.getArtistId());
//		System.out.println(a.getBeginDateYear());
		System.out.println("");

	}

	public Class<? extends ID> getBaseClass() {
		return baseClass;
	}


	public ID getId() {
		return id;
	}

}
