package org.nanotek.proxy;

import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean.METHOD_TYPE;
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
		return visitMethod(method);
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
	
	private boolean visitMethod(Method method) {
		 boolean found=false;
		 	METHOD_TYPE mtype  = METHOD_TYPE.READ;
			Class<?>[] parameters = method.getParameterTypes();
			Class<?> returnType  = method.getReturnType();
			Class<?> classId = method.getDeclaringClass();
			if (method.getParameters() == null) { 
				mtype  = METHOD_TYPE.READ;
			}else {
				mtype = METHOD_TYPE.WRITE;
			}
			MethodType mt = MethodType.methodType(returnType, mtype.equals(METHOD_TYPE.WRITE)?parameters:new Class[] {});
			MethodHandle mh = null;
			try {
					mh = lookup.findVirtual(classId, method.getName(), mt);
					found = true;
			} catch (Exception e) {
				e.printStackTrace();
				return found;
			}			
		return found;
	}
	
	public static void main(String[] main) {
		AreaBean pbb = new AreaBean(Area.class);
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
