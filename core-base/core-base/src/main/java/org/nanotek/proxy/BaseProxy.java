package org.nanotek.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.Area;
import org.nanotek.proxy.entities.BaseAreaEntityProxy;

import jdk.internal.reflect.CallerSensitive;

public 	class BaseProxy extends Proxy 
implements InvocationHandler{

	private ProxyBaseBean<?, ?> proxyBaseBean;
	
	private Map<UUID, Class<?>> instanceRegistryClass;
	
	private Map<UUID,Object> proxyRegistry;
	
	private Class<?> currentClass;

	public BaseProxy(InvocationHandler h) {
		super(withDelegateBaseProxy());
	}

	public BaseProxy(ProxyBaseBean<?,?> pbb) {
		super(pbb);
		this.proxyBaseBean = pbb;
		registerIntance(pbb);
	}
	
	private void registerIntance(ProxyBaseBean<?, ?> pbb) {
		instanceRegistryClass = new  HashMap<>();
		instanceRegistryClass.put(Base.withUUID(pbb.getBaseClass()), pbb.getBaseClass());
	}

	private static InvocationHandler withDelegateBaseProxy() {
		return new ProxyBaseBean<>();
	}

	
	@CallerSensitive
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h) {
		return Proxy.newProxyInstance(loader, interfaces, h);
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> ownerClass = instanceRegistryClass.get(Base.withUUID(Area.class));
		if (args != null && args.length == 1)
			return proxyBaseBean.writeA(method.getDeclaringClass(), args[0]);
		else if (args == null) {
			Object value =  proxyBaseBean.readB(method.getDeclaringClass()).orElse(null);
			return value;
		}
		return null;
	}
	
	public static void main(String[] main) {
		ProxyBaseBean pbb = new ProxyBaseBean(Area.class);
		BaseProxy proxy = new BaseProxy(pbb);
		Object sb1 = BaseProxy.
						newProxyInstance(ProxyBaseBean.class.getClassLoader(), 
								new Class[] {BaseAreaEntityProxy.class},
								proxy);
		 BaseAreaEntityProxy slb = BaseAreaEntityProxy.class.cast(sb1);
		 slb.setAreaId(100L);
		 slb.getAreaEndDate().setEndYear(200);
		 System.out.println(slb.getAreaId());
		 System.out.println(slb.getEndYear());
	}
	
}
