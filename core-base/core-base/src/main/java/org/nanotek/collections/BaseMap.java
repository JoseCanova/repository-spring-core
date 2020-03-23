package org.nanotek.collections;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.TreeMap;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseException;
import org.nanotek.beans.csv.AreaBean;
	
/**
 * 
 * @author josecanovamauger.
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class BaseMap<T  extends AnyBase<T,C> , C extends Comparable<C> , D extends Base<T>>
extends  TreeMap<T,C> {

	protected D immutable;
	
	public <K extends Comparable, V extends Comparable> BaseMap() {
		super();	
		immutable = null;
	}
	
	public BaseMap(D k) {
		super();
		immutable = k;
	}
	
	
	public void afterPropertiesSet() {
		Class<?> clazz = Optional.ofNullable(immutable).orElseThrow(BaseException::new).getClass();
		System.out.println(clazz.getName());
		Field[] fields = clazz.getDeclaredFields();
		Arrays.asList(fields).stream().forEach(f->{
			put(AnyBase.of(f.getName()).asBase(),(C) AnyBase.of(0L).asBase());
			System.out.println(f.getName());
		});
		Object value = get(AnyBase.of("id").asBase());
		System.out.println(value);
		Optional.ofNullable(AnyBase.of("id").asBase()).filter(v ->
					keySet().contains(v)).orElseThrow(BaseException::new);
	}

	public static void main(String [] args) { 
		BaseMap n = new BaseMap(new AreaBean());
		Base a = AnyBase.of("id").asBase();
		Base b = AnyBase.of("id").asBase();
		System.out.println(a.equals(b));
		System.out.println(a.compareTo(b));
		System.out.println(AnyBase.of("id").asBase().withUUID());
		System.out.println(AnyBase.of("id").asBase().withUUID());
		n.afterPropertiesSet();
	}

}