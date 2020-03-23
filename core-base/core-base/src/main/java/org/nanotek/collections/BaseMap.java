package org.nanotek.collections;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.collections4.bidimap.TreeBidiMap;
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
extends TreeBidiMap<T,C> {

	protected D immutable;
	
	public BaseMap() {
		immutable = null;
	}
	
	public BaseMap(D k) {
		immutable = k;
	}
	
	
	public void afterPropertiesSet() {
		Class<?> clazz = Optional.ofNullable(immutable).orElseThrow(BaseException::new).getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		Arrays.asList(fields).stream().forEach(f->{
			AnyBase.of(0L);
			put(AnyBase.of(f.getName()).asBase(),(C) AnyBase.of(0L).asBase());
		});
		Optional.ofNullable(keySet().contains("immutable")).orElseThrow(BaseException::new);
	}

	public static void main(String [] args) { 
		BaseMap n = new BaseMap(new AreaBean());
		n.afterPropertiesSet();
	}

}