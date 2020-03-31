package org.nanotek.proxy;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.nanotek.Base;

public class BaseInvocationHandler<K extends Base<?>> 
implements InvocationHandler{

	private K base;
	private Class<? extends Base> baseClass;
	
	private MethodHandles.Lookup lookup = MethodHandles.lookup();
	private BaseProxy<?> delegateInvocationHander;

	public BaseInvocationHandler() {
	}

	public BaseInvocationHandler(K base) {
		this.base = base;
		this.baseClass = base.getClass();
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return delegateInvocationHander.invoke(proxy, method, args);
	}

	public K getBase() {
		return base;
	}

	@SuppressWarnings("rawtypes")
	public Class<? extends Base> getBaseClass() {
		return baseClass;
	}

	public void setDelegateInvocationHandler(BaseProxy<?> baseProxy) {
			this.delegateInvocationHander = baseProxy;
	}

}
