package org.nanotek.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
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

	public static <K extends Area<K>>  void  main(String[] args) { 
		ProxyBaseBean<?,Area<?>> pbb = new ProxyBaseBean<>(Area.class);
		List<? super Class<?>> insterf = pbb
					.getChildInterfaceMap().get(Area.class)
					.values().stream()
					.map(h->h.getClazz())
					.collect(Collectors.toList());
		insterf.add(BaseAreaEntity.class);
		Object sb1 = java.lang.reflect.Proxy.newProxyInstance(
				Area.class.getClassLoader(),
				insterf.toArray(new Class[insterf.size()]),
				pbb);

		//	   sb1.getId();
//		BaseAreaEntity<K> slb = BaseAreaEntity.class.cast(sb1);
//		slb.setAreaId(100L);
//		slb.getAreaBeginDate().setBeginYear(100);
//		System.out.println(slb.getAreaId());
//		System.out.println(slb.getAreaBeginDate().getBeginYear());
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
