package org.nanotek.proxy;

import java.beans.PropertyChangeSupport;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.Medium;
import org.nanotek.entities.MutableAreaIdEntity;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.entities.immutables.AreaIdEntity;
import org.nanotek.entities.immutables.ArtistIdEntity;

public class ProxyBase
<K extends ImmutableBase<K,ID> , ID extends BaseEntity<?,Long>> 
implements BaseBean<K,ID>
{

	private static final long serialVersionUID = -1465843449151457466L;

	private MethodHandles.Lookup lookup = MethodHandles.lookup();

	public ID id;

	protected Class<? extends ID> baseClass;

	protected PropertyChangeSupport propertyChangeSupport = null;

	private HashMap<Class<?> , HashMap<Class<?> , BaseBean.ClassHandle<?>>> childInterfaceMap;
	
	private HashMap<Class<?> , BaseEntity<?,?>> instanceMap;


	public ProxyBase(Class<? extends ID> class1) {
		baseClass = class1;
		id =  class1.cast(ProxyBase.prepareBeanInstance(class1.asSubclass(IdBase.class)));
		mountInstanceMap();
		configureBaseBean();
	}
	


	@SuppressWarnings("unchecked")
	public static IdBase<?,?> prepareBeanInstance(Class<?> idBase) { 
		return IdBase.prepareBeanInstance(idBase.asSubclass(IdBase.class));
	}

	public void configureBaseBean() {
		childInterfaceMap = new HashMap<>();
		registryDynaBean();
	}
	
	@Override
	public void mountInstanceMap() {
		getInstanceMap().put(getBaseClass(),getId());
		getFields(getBaseClass())
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
	

	private List<Field> getFields(Class<? extends ID> baseClass2) {
		return Arrays.asList(baseClass2.getClasses())
					.stream()
					.filter(c ->!c.isInterface())
					.map(c ->c.getFields())
					.filter(fa -> fa!=null && fa.length>0)
					.map(fa -> Arrays.asList(fa))
					.collect(Collectors.toList())
					.stream().flatMap(childList -> childList.stream())
					.collect(Collectors.toList());
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
			MethodHandle mh = null;
			try {
				switch(mtype) {
				case WRITE:
					if(f.getName().equals(atributeName)){
						mh = lookup.findSetter(classId.asSubclass(BaseEntity.class), f.getName(), f.getType());
						found = true;
					}else if(Arrays.asList(classId.getDeclaredClasses()).contains(clazz)){
						mh = lookup.findVirtual(classId.asSubclass(BaseEntity.class), method.getName(), mt);//my guess this is wrong.
						found = true;
					}
					break;
				case READ:
					if(f.getName().equals(atributeName)){
						mh = lookup.findGetter(classId, f.getName(), f.getType());
						found = true;
					}
					else{//just to not enter on  this section.. check this later on jvm docs.
						mh = lookup.findVirtual(clazz, method.getName(), mt);
						found = true;
					}
					break;
				default:
					mh = lookup.findVirtual(clazz, method.getName(), mt);
					if (classId.getClass().equals(getId().getClass()) && mh !=null) {
						mh.bindTo(getId());
						found = true;
					}
					break;
				}
				Optional.ofNullable(mh).ifPresentOrElse(
											mhandle ->
														childInterfaceMap.put(classId  ,   
														verifyAndStore(classId,clazz,mhandle,mtype,mt)),
														()->notFound(clazz));
			} catch (Exception e) {
				e.printStackTrace();
				return found;
			}			
		return found;
	}

	//TODO: put log here.
	private void notFound(Class<?> clazz) {
	}

	private HashMap<Class<?>, ClassHandle<?>> verifyAndStore(Class<?> classId, Class<?> clazz, MethodHandle mh,
			METHOD_TYPE mtype, MethodType mt) {
		HashMap<Class<?>, BaseBean.ClassHandle<?>> map = childInterfaceMap.get(classId);
		if (map ==null) { 
			map = new HashMap<Class<?>, BaseBean.ClassHandle<?>>();
		}
		store(map,clazz,prepareClassHandle(clazz,mh,mtype,mt));
		return map;
	}


	private ClassHandle<?> prepareClassHandle(Class<?> clazz, MethodHandle mh, METHOD_TYPE mtype,MethodType mt) {
		return new ClassHandle<>(clazz,mh,mtype,mt);
	}

	private void store(HashMap<Class<?>, ClassHandle<?>> map, Class<?> clazz, 
								ClassHandle<?> classHandle) {
		map.put(clazz, classHandle);
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
			return Optional.empty();

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
			return Optional.empty();
		}));
	}
	
	public ProxyBase<?,?> getReference(){
		return this;
	}

	public static void main(String[] args) { 
		ProxyBase<?,Artist<?>> a = new ProxyBase<>(Artist.class);
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


	public HashMap<Class<?>, HashMap<Class<?>, BaseBean.ClassHandle<?>>> getChildInterfaceMap() {
		return childInterfaceMap;
	}


	public void setChildInterfaceMap(HashMap<Class<?>, HashMap<Class<?>, BaseBean.ClassHandle<?>>> childInterfaceMap) {
		this.childInterfaceMap = childInterfaceMap;
	}


	public MethodHandles.Lookup getLookup() {
		return lookup;
	}


	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = this.getClass().cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}
	
}
