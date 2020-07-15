package org.nanotek.proxy;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean.METHOD_TYPE;
import org.nanotek.BaseException;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.entities.BaseAreaBean;

public 	class BaseProxy<K extends Base<K>> extends Proxy
implements InvocationHandler , Base<K>{
	
	private MethodHandles.Lookup lookup = MethodHandles.lookup();
	
	private Map<UUID, Class<?>> instanceClassRegistry;
	
	private Map<UUID,Object> proxyRegistry;
	
	private Object instance;
	
	private BaseProxy(InvocationHandler h , boolean bo) {
		super(h);
	}

	public BaseProxy(BaseInvocationHandler<?> pbb) {
		super(withDelegateBaseProxy(pbb));
		instance = pbb.getBase();
		pbb.setDelegateInvocationHandler(this);
	}
	
	private static InvocationHandler withDelegateBaseProxy(BaseInvocationHandler<?> pbb) {
		return new  BaseProxy(InvocationHandler.class.cast(pbb) , true);
	}

	private static InvocationHandler withDelegateBaseProxy() {
		return new BaseInvocationHandler<>();
	}

	
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h) {
		return Proxy.newProxyInstance(loader, interfaces, h);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(instance, args);
	}
	
	
	public static void main(String[] main) {
		AreaBean pbb = new AreaBean();
		BaseInvocationHandler ih = new BaseInvocationHandler(pbb);
		BaseProxy proxy = new BaseProxy(ih);
		Object sb1 = BaseProxy.
								newProxyInstance(ProxyBaseBean.class.getClassLoader(), 
								new Class[] {BaseAreaBean.class},
								ih);
		BaseAreaBean slb = BaseAreaBean.class.cast(sb1);
		System.out.println("'");
		System.out.println(slb.getAreaId());
		slb.setAreaId(100L);
		slb.setEndYear(300);
		System.out.println(slb.getAreaId());
		System.out.println(slb.getEndYear());
	}
	
}
