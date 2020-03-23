package org.nanotek.collections;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.TreeMap;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseException;
import org.nanotek.beans.csv.ArtistBean;
	
/**
 * 
 * @author josecanovamauger.
 * this bean is designed to read the description of the csv file in the application.
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class BaseMap<K  extends AnyBase<K,?> , V   extends Base<V>, D extends Base<?>>
extends  TreeMap<K,V> {

	protected D immutable;
	
	public BaseMap() {
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
		
		int pos = 0;
		for (Field f : fields) { 
			put(AnyBase.of(f.getName()).asBase(),AnyBase.of(pos++).asBase());
		}
	}

	public static void main(String [] args) { 
		BaseMap n = new BaseMap(new ArtistBean());
		Base a = AnyBase.of("id").asBase();
		Base b = AnyBase.of("id").asBase();
		System.out.println(a.equals(b));
		System.out.println(a.compareTo(b));
		System.out.println(AnyBase.of("id").asBase().withUUID());
		System.out.println(AnyBase.of("id").asBase().withUUID());
		n.afterPropertiesSet();
	}

}