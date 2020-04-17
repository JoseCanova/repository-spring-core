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
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.BaseException;
import org.nanotek.IdBase;
import org.nanotek.ImmutableBase;

public class ProxyBase
<K extends ImmutableBase<K,ID> , ID extends BaseEntity<?,Long>> 
implements BaseBean<K,ID>
{

	private static final long serialVersionUID = -1465843449151457466L;

	private static MethodHandles.Lookup lookup = MethodHandles.lookup();

	public ID id;

	protected Class<? extends ID> baseClass;

	protected PropertyChangeSupport propertyChangeSupport = null;

	private static HashMap<Class<?> , HashMap<Class<?> , BaseBean.ClassHandle<?>>> 
						childInterfaceMap = new  HashMap<Class<?> , HashMap<Class<?> , BaseBean.ClassHandle<?>>> ();
	
	private static Semaphore semaphore = new Semaphore(1);
	
	private static AtomicBoolean isConfigured = new AtomicBoolean(false);
	
	public boolean  isConfigured() {
		try {
			semaphore.acquire();
			if(childInterfaceMap.get(this.baseClass)!=null) {
				setConfigured(true);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return isConfigured.get();
	}
	
	public void setConfigured(boolean b) {
		isConfigured.set(b);
	}
	
	private HashMap<Class<?> , BaseEntity<?,?>> instanceMap;


	public ProxyBase(Class<? extends ID> class1) {
		super();
		baseClass = class1;
		configureBaseBean();
		postConstruct();
	}
	
	
	private void postConstruct() {
		id =  baseClass.cast(ProxyBase.prepareBeanInstance(baseClass.asSubclass(IdBase.class)));
		prepareMap();	
	}

	private void prepareMap() {
		mountInstanceMap();		
	}

	@SuppressWarnings("unchecked")
	public static IdBase<?,?> prepareBeanInstance(Class<?> idBase) { 
		return IdBase.prepareBeanInstance(idBase.asSubclass(IdBase.class));
	}

	private void configureBaseBean() {
		if(!isConfigured()) {
				System.out.println("configuring");
				registryDynaBean();
		}
		semaphore.release();
	}
	
	@Override
	public void mountInstanceMap() {
		getInstanceMap().put(getBaseClass(),getId());
		getFields(getBaseClass())
					.stream()
					.filter(f->BaseEntity.class.isAssignableFrom(f.getType()))
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
						mh = lookup.findSetter(classId, f.getName(), f.getType());
						found = true;
					}else if(Arrays.asList(classId.getDeclaredClasses()).contains(clazz)){
						mh = lookup.findVirtual(classId, method.getName(), mt);//my guess this is wrong.
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
					if (classId.getClass().equals(getBaseClass()) && mh !=null) {
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
				return this.compareTo((K) theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}
	
}
