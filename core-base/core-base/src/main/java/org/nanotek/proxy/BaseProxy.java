package org.nanotek.proxy;

import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean.METHOD_TYPE;
import org.nanotek.BaseException;
import org.nanotek.MutatorSupport;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.entities.BaseAreaBean;

import jdk.internal.reflect.CallerSensitive;

public 	class BaseProxy<K extends Base<K>> extends Proxy
implements InvocationHandler , Base<K>{
	
	private MethodHandles.Lookup lookup = MethodHandles.lookup();
	
	private Map<UUID, Class<?>> instanceClassRegistry;
	
	private Map<UUID,Object> proxyRegistry;
	

	public BaseProxy(InvocationHandler h , boolean bo) {
		super(h);
	}

	public BaseProxy(BaseInvocationHandler<?> pbb) {
		super(withDelegateBaseProxy(pbb));
		registerInstance(pbb);
		pbb.setDelegateInvocationHandler(this);
	}
	
	private static InvocationHandler withDelegateBaseProxy(BaseInvocationHandler<?> pbb) {
		return new  BaseProxy(InvocationHandler.class.cast(pbb) , true);
	}

	private void registerInstance(BaseInvocationHandler<?> pbb) {
		instanceClassRegistry = new  HashMap<>();
		instanceClassRegistry.put(Base.withUUID(pbb.getBaseClass()), pbb.getBaseClass());
	}

	private static InvocationHandler withDelegateBaseProxy() {
		return new BaseInvocationHandler<>();
	}

	
	@CallerSensitive
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h) {
		return Proxy.newProxyInstance(loader, interfaces, h);
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object returnType = visitMethod(method,args).invoke(args !=null?method.getParameters()[0].getType().cast(args[0]):args);
		return  null;
	}
	
	public boolean registerInterface(Class<?> interf ,  Method method) {
		Optional<PropertyDescriptor> pd = MutatorSupport.getPropertyDescriptor(interf);
		return	pd.map(p-> {
			boolean result = false;
			if(p.getWriteMethod() !=null) { 
//				result = registryMethod(classId , p.getWriteMethod().getDeclaringClass() , p.getName() , p.getWriteMethod(),METHOD_TYPE.WRITE);
			}
			if(p.getReadMethod() !=null) {
//				result = registryMethod(classId , p.getReadMethod().getDeclaringClass() , p.getName() , p.getReadMethod(),METHOD_TYPE.READ);
			}
			return result;
		}).filter(r -> r==true).orElse(false);

	}
	
	private MethodHandle visitMethod(Method method, Object[] args) {
		 boolean found=false;
		 	METHOD_TYPE mtype  = METHOD_TYPE.READ;
			Class<?>[] parameters = method.getParameterTypes();
			Class<?> returnType  = method.getReturnType();
			Class<?> classId = method.getDeclaringClass();
			String fieldName = null;
			if (method.getParameters() == null) { 
				mtype  = METHOD_TYPE.READ;
			}else {
				mtype = METHOD_TYPE.WRITE;
			}
			
			MethodType mt = MethodType.methodType(returnType, mtype.equals(METHOD_TYPE.WRITE)?parameters:new Class[] {});
			MethodHandle mh = null;
			try {	switch (mtype) { 
						case READ:
							mh = lookup.findVirtual(classId, method.getName(), mt).asType(mt);//lookup.findGetter(Area.class, fieldName, method.getReturnType());
							break;
						case WRITE:
							Class<?> clazz = method.getParameterTypes()[0];
							mh = lookup.findVirtual(classId, method.getName(), mt).asType(mt);//lookup.findSetter(Area.class, fieldName, clazz);
							break;
						default:
							mh = lookup.findVirtual(classId, method.getName(), mt);
							break;
					}
					
					found = true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new BaseException(e);
			}			
		return mh;
	}
	
	public static void main(String[] main) {
		Area pbb = new Area();
		BaseInvocationHandler ih = new BaseInvocationHandler(pbb);
		BaseProxy proxy = new BaseProxy(ih);
		Object sb1 = BaseProxy.
								newProxyInstance(ProxyBaseBean.class.getClassLoader(), 
								new Class[] {BaseAreaBean.class},
								ih);
		BaseAreaBean slb = BaseAreaBean.class.cast(sb1);
		System.out.println("'");
		slb.setAreaId(100L);
		slb.setEndYear(300);
		System.out.println(slb.getAreaId());
		System.out.println(slb.getEndYear());
	}
	
}
