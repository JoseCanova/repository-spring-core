package org.nanotek.collections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.TreeMap;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.proxy.ProxyBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
	
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
	
	private static Logger log = LoggerFactory.getLogger(BaseMap.class.getName());

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

	//TODO:implement a log line here.
	public void afterPropertiesSet() {
		log.debug("VERIFYING CONFIGURATION MAP");
		keySet().forEach(k ->{
			log.debug(get(k).withUUID().toString());
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
		Artist<?> ar = new Artist<>();
		ar.setId(100L);
	}
    
	
//	public V put(String value, Integer position) {
//		return super.put(AnyBase.of(value).asBase(), AnyBase.of(position).asBase());
//	}
	
}