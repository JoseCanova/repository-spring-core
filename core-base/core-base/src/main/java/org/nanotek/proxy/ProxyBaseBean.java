package org.nanotek.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.entities.BaseAreaEntity;
import org.nanotek.proxy.map.bean.ForwardMapBean;

public class ProxyBaseBean
<K extends BaseBean<K,ID> , ID extends SequenceLongBase<?, Long>>
extends ProxyBase<K,ID> implements InvocationHandler{

	private static final long serialVersionUID = 6975012634371691413L;

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
	
	private static <X> ForwardMapBean<?> asDynaMapBean(Class<X> clazz) { 
		ProxyBaseBean<?,?> pbb = new ProxyBaseBean(clazz); 
		List<? super Class<?>> insterf = pbb
				.getChildInterfaceMap().get(clazz)
				.values().stream()
				.map(h->h.getClazz())
				.collect(Collectors.toList());
		insterf.add(Base.class);
		Object sb1 = java.lang.reflect.Proxy.newProxyInstance(
				clazz.getClassLoader(),
				insterf.toArray(new Class[insterf.size()]),
				pbb);
		return new ForwardMapBean<>(sb1.getClass() ,  Base.class.cast(sb1));
		
	}

	public static <K extends Area<K>>  void  main(String[] args) { 
//		ProxyBaseBean<?,Area<?>> pbb = new ProxyBaseBean<>(Area.class);
//		List<? super Class<?>> insterf = pbb
//					.getChildInterfaceMap().get(Area.class)
//					.values().stream()
//					.map(h->h.getClazz())
//					.collect(Collectors.toList());
//		insterf.add(BaseAreaEntity.class);
//		insterf.add(Base.class);
//		Object sb1 = java.lang.reflect.Proxy.newProxyInstance(
//				Area.class.getClassLoader(),
//				insterf.toArray(new Class[insterf.size()]),
//				pbb);
//		Base sb2  = (Base)sb1;
		ForwardMapBean<?> dm= asDynaMapBean(Area.class);
		dm.write("areaName" , "this  is a name");
		System.out.println(dm.read("areaName").toString());
		ForwardMapBean<?> dm1= asDynaMapBean(Artist.class);
		dm1.write("artistName" , "this  is an artist name");
		System.out.println(dm1.read("artistName").toString());
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
