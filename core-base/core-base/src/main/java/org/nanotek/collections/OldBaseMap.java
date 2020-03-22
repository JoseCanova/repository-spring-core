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
public class OldBaseMap<T extends BaseBean<?,?>> 
	extends HashMap<String,Integer> {

	protected T immutable;
	
	public OldBaseMap() {
	}
	
	public OldBaseMap(T immutable) {
		super();
		this.immutable = immutable;
	}
 
	
}
