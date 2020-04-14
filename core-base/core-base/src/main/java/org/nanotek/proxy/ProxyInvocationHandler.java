package org.nanotek.proxy;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.nanotek.entities.BaseAreaBean;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public class ProxyInvocationHandler 
implements InvocationHandler{

	private Object base;
	private Class<?> baseClass;
	
	private MethodHandles.Lookup lookup = MethodHandles.lookup();

	public ProxyInvocationHandler() {
	}

	public ProxyInvocationHandler(Object base) {
		this.baseClass = base.getClass();
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return true;
	}




	public Object getBase() {
		return base;
	}

	public void setBase(Object base) {
		this.base = base;
	}

	public Class<?> getBaseClass() {
		return baseClass;
	}

	public void setBaseClass(Class<?> baseClass) {
		this.baseClass = baseClass;
	}

	public MethodHandles.Lookup getLookup() {
		return lookup;
	}

	public void setLookup(MethodHandles.Lookup lookup) {
		this.lookup = lookup;
	}

//TODO: FIX THE PROBLEM.
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Class<?> type = new ByteBuddy(ClassFileVersion.JAVA_V8)
				  .subclass(Object.class)
				  .implement(BaseAreaBean.class)
				  .make()
				  .load(BaseAreaBean.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER_PERSISTENT)
				  .getLoaded();
		BaseAreaBean object = (BaseAreaBean) type.newInstance();
		object.setAreaId(100L);
		System.out.println();
	}
	
	
}
