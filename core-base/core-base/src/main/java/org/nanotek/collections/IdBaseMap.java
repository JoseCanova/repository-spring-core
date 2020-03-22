package org.nanotek.collections;

import java.util.HashMap;

import org.nanotek.beans.csv.BaseBean;

/**
 * 
 * @author josecanovamauger.
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class IdBaseMap<T extends BaseBean<?,?>> 
	extends HashMap<String,Integer> {

	protected T immutable;
	
	public IdBaseMap() {
	}
	
	public IdBaseMap(T immutable) {
		super();
		this.immutable = immutable;
	}
 
	
}
