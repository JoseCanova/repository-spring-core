package org.nanotek.collections;

import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.nanotek.Base;

/**
 * 
 * @author josecanovamauger.
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class BaseMap<T extends Base<T> , ID extends Comparable<ID>> 
	extends TreeBidiMap<T,ID>{

	protected T immutable;
	
	public BaseMap() {
	}
	
	public BaseMap(T immutable) {
		super();
		this.immutable = immutable;
	}

}