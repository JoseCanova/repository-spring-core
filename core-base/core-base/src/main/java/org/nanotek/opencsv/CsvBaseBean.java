package org.nanotek.opencsv;

import java.beans.PropertyChangeSupport;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.MutableAreaIdEntity;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.entities.immutables.AreaIdEntity;
import org.nanotek.entities.immutables.ArtistIdEntity;

public class CsvBaseBean
<K extends ImmutableBase<K,ID> , ID extends BaseEntity<?,Long>> 
implements BaseBean<K,ID>
{

	private static final long serialVersionUID = -1465843449151457466L;

	private MethodHandles.Lookup lookup = MethodHandles.lookup();

	private ID id;

	protected Class<? extends ID> baseClass;

	protected PropertyChangeSupport propertyChangeSupport = null;

	private HashMap<Class<?> , HashMap<Class<?> , BaseBean.ClassHandle<?>>> childInterfaceMap;
	
	private HashMap<Class<?> , BaseEntity<?,?>> instanceMap;


	public CsvBaseBean(Class<? extends ID> class1) {
		baseClass = class1;
		id =  class1.cast(CsvBaseBean.prepareBeanInstance(class1.asSubclass(IdBase.class)));
		mountInstanceMap();
		configureBaseBean();
	}


	@SuppressWarnings("unchecked")
	private static IdBase<?,?> prepareBeanInstance(Class<?> idBase) { 
		return IdBase.prepareBeanInstance(idBase.asSubclass(IdBase.class));
	}

	private void configureBaseBean() {
		childInterfaceMap = new HashMap<>();
		registryDynaBean();
	}
	
	@Override
	public void mountInstanceMap() {
		Class<? extends ID> baseEntity = getBaseClass();
		getInstanceMap().put(getBaseClass(),getId());
		Arrays.asList(baseEntity.getFields())
					.stream()
					.filter(f->f.getType().getPackageName().contains("org.nanotek.beans.entity"))
					.forEach(f -> {
						try { 
								BaseEntity<?,?> entity ;
								try { 
									entity = (BaseEntity<?, ?>) f.get(id);
								}catch(Exception ex) {
									throw new BaseException (ex);
								}
								f.set(id, entity);
								getInstanceMap().put(f.getType(), entity);
						}catch  (Exception ex) { 
							throw new BaseException(ex);
						}
					});
	}
	

	public Optional<PropertyChangeSupport> getPropertyChangeSupport() {
		return Optional.ofNullable(propertyChangeSupport);
	}

	public MethodHandle getAtributeNameInstance(Class<?> ownerClass,Class<?> interfaceClass) {
		return childInterfaceMap.get(ownerClass).get(interfaceClass).getMethodHandle();
	}



	public boolean registryMethod(Class<?> classId, Class<?> clazz, String atributeName, Method method , BaseBean.METHOD_TYPE mtype) {
		Long numFound = Arrays.asList(classId.getFields()).stream().map(f ->{
			return visitField(classId , f,clazz,atributeName,method,mtype);
		}).filter(b -> b== true).count();
		return numFound>0?true:false;
	}

	private boolean visitField(Class<?> classId, Field f, Class<?> clazz, String atributeName, Method method, METHOD_TYPE mtype) {
		boolean found=false;
			Class<?>[] parameters = method.getParameterTypes();
			Class<?> returnType  = method.getReturnType();
			MethodType mt = MethodType.methodType(returnType, mtype.equals(METHOD_TYPE.WRITE)?parameters:new Class[] {});
			MethodHandle mh;
			try {
				switch(mtype) {
				case WRITE:
					if(f.getName().equals(atributeName)){
						mh = lookup.findSetter(classId.asSubclass(BaseEntity.class), f.getName(), f.getType());
						childInterfaceMap.put(classId,   verifyAndStore(classId,clazz,mh));
					}else if(Arrays.asList(classId.getDeclaredClasses()).contains(clazz)){
						mh = lookup.findVirtual(classId.asSubclass(BaseEntity.class), method.getName(), mt);
						childInterfaceMap.put(classId,   verifyAndStore(classId,clazz,mh));
					}
					break;
				case READ:
					if(f.getName().equals(atributeName)){
						mh = lookup.findGetter(classId, f.getName(), f.getType());
						childInterfaceMap.put(classId, verifyAndStore(classId,clazz,mh));
					}else {
						mh = lookup.findVirtual(clazz, method.getName(), mt);
						childInterfaceMap.put(classId,   verifyAndStore(classId,clazz,mh));
					}
					break;
				default:
					mh = lookup.findVirtual(clazz, method.getName(), mt);
					if (classId.getClass().equals(getId().getClass())) {
						childInterfaceMap.put(classId,   verifyAndStore(classId,clazz,mh));
						mh.bindTo(getId());
					}else {
						childInterfaceMap.put(classId, verifyAndStore(classId,clazz,mh));
					}
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}			
			found = true;
		return found;
	}


	private <U extends MethodHandle> HashMap<Class<?>, ClassHandle<?>> verifyAndStore(Class<?> classId, Class<?> clazz, MethodHandle mh) {
		HashMap<Class<?>, BaseBean.ClassHandle<?>> map = childInterfaceMap.get(classId);
		if (map !=null) { 
			map.put(clazz, new ClassHandle<>(clazz,mh));
		}else { 
			map = new HashMap<Class<?>, BaseBean.ClassHandle<?>>();
			map.put(clazz, new ClassHandle<>(clazz,mh));
		}
		return map;
	}
	
	public <V> Optional<V> writeA(Class<?> clazz , V v){
		return Optional.ofNullable(childInterfaceMap.get(baseClass).get(clazz)).map(f -> 
		{
			try {
				f.getMethodHandle().invoke(this.getId(),v);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return v;});
	}

	public <V> Optional<V> writeA(Class<?>ownerClass,Class<?> clazz , V v){
		return Optional.ofNullable(Optional.ofNullable(childInterfaceMap.get(ownerClass)).map(f -> 
		{
			try {
				f.get(clazz).getMethodHandle().invoke(instanceMap.get(ownerClass),v);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return v;}).orElseThrow(BaseException::new));
	}

	
	public Optional<?> readB(Class<?> clazz){
		return Optional.ofNullable(childInterfaceMap.get(baseClass).get(clazz)).map(f -> 
		{
			try {
				return f.getMethodHandle().invoke(this.getId());
			}catch(Throwable ex) {
				ex.printStackTrace();
			}
			return null;

		});
	}

	public Optional<?> readB(Class<?> ownerClass,Class<?> clazz){
		return Optional.ofNullable(Optional.ofNullable(childInterfaceMap.get(ownerClass)).map(f -> 
		{
			try {
				return f.get(clazz).getMethodHandle().invoke(instanceMap.get(ownerClass));
			}catch(Throwable ex) {
				ex.printStackTrace();
			}
			return null;
		}));
	}
	public CsvBaseBean<?,?> getReference(){
		return this;
	}

	public static void main(String[] args) { 
		CsvBaseBean<?,Artist<?>> a = new CsvBaseBean<>(Artist.class);
				a.write(MutableArtistIdEntity.class, 1000L);
				a.read(ArtistIdEntity.class);
				a.write(Area.class, MutableAreaIdEntity.class,1000L);
				a.read(Area.class, AreaIdEntity.class);
				System.out.println(a.read(ArtistIdEntity.class));
				System.out.println(a.read(Area.class, AreaIdEntity.class));
	}

	public Class<? extends ID> getBaseClass() {
		return baseClass;
	}

	public HashMap<Class<?> , BaseEntity<?,?>> getInstanceMap(){
		return instanceMap == null ? instanceMap = new HashMap<>(): instanceMap;
	}

	public ID getId() {
		return id;
	}

}
