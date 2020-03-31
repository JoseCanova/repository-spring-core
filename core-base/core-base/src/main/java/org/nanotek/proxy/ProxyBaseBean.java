package org.nanotek.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Optional;

import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.entities.BaseAreaBean;
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
	   ProxyBaseBean<?,Area<?>> pbb = new ProxyBaseBean<>(Area.class);
		   Object sb1 = java.lang.reflect.Proxy.newProxyInstance(
			   	Area.class.getClassLoader(),
			   	Area.class.getInterfaces(),
	            pbb);
       
//	   sb1.getId();
	   BaseAreaBean slb = BaseAreaBean.class.cast(sb1);
	   slb.setAreaId(100L);
	   AreaBeginDate.class.cast(slb.getAreaBeginDate()).setBeginYear(100);
	   System.out.println(slb.getAreaId());
	   System.out.println(AreaBeginDate.class.cast(slb.getAreaBeginDate()).getBeginYear());
   }
   
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (args != null && args.length == 1)
			return writeA(method.getDeclaringClass(), args[0]);
		else if (args == null) {
			Object value =  readB(method.getDeclaringClass()).orElse(null);
			return value;
		}
		return null;
	}

}
