package org.nanotek.opencsv;

import java.util.HashMap;

import org.nanotek.beans.csv.BaseBean;

/**
 * 
 * @author josecanovamauger.
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class BaseMap<T extends BaseBean<?,?>> 
	extends HashMap<String,Integer> {

	protected T immutable;
	
	public BaseMap() {
	}
	
	public BaseMap(T immutable) {
		super();
		this.immutable = immutable;
	}
 
	
}
