package org.nanotek.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Optional;

import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.entities.BaseAreaEntity;


public class ProxyBaseBean
<K extends BaseBean<K,ID> , ID extends SequenceLongBase<?, Long>>
extends ProxyBase<K,ID> implements InvocationHandler{

	public ProxyBaseBean() {
		super((Class<? extends ID>) withAnyEntity(null));
	}

	public ProxyBaseBean(Class<? extends ID> class1) {
		super(class1);
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends SequenceLongBase<?,?>> withAnyEntity(Class<?> clazz) {
		return (Class<? extends SequenceLongBase<?, ?>>) Optional.ofNullable(clazz).orElseThrow(BaseException::new);
	}

   public static void main(String[] args) { 
	   
	   ProxyBaseBean pbb = new ProxyBaseBean(Artist.class);
	   Object sb1 = java.lang.reflect.Proxy.newProxyInstance(
			   	Area.class.getClassLoader(),
			   	Area.class.getInterfaces(),
	            pbb);
	   Object sb2 = java.lang.reflect.Proxy.newProxyInstance(
			   	Area.class.getClassLoader(),
			   	Area.class.getInterfaces(),
	            pbb);
//	   sb1.getId();
	   BaseAreaEntity slb = BaseAreaEntity.class.cast(sb1);
	   slb.setAreaId(100);
	   System.out.println("");
	   
   }
   
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(method.getDeclaringClass());
		return null;
	}

}
