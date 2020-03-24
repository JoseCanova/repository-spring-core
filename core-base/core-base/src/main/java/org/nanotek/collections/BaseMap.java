package org.nanotek.collections;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.TreeMap;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseException;
import org.nanotek.beans.csv.ArtistBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
	
/**
 * 
 * @author josecanovamauger.
 * this bean is designed to read the description of the csv file in the application.
 *
 * @param <T>
 */
@SuppressWarnings("serial")
//@Component(value = "BaseMap")S
public class BaseMap<K  extends AnyBase<K,?> , V   extends Base<V>, D extends Base<?>>
extends  TreeMap<K,V> implements InitializingBean {

	protected D immutable;
	
	public <S  extends AnyBase<S,String> , P   extends AnyBase<P,Integer>>  BaseMap() {
		super();	
		immutable = null;
	}
	
	public BaseMap(D k) {
		super();
		immutable = k;
	}
	
	public D getImmutable() {
		return immutable;
	}

	public void setImmutable(D immutable) {
		this.immutable = immutable;
	}

	public void afterPropertiesSet() {
		System.out.println("VERIFYING CONFIGURATION MAP");
		keySet().forEach(k ->{
			System.out.println(get(k).withUUID());
		});
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

//	public V put(String value, Integer position) {
//		return super.put(AnyBase.of(value).asBase(), AnyBase.of(position).asBase());
//	}
	
}