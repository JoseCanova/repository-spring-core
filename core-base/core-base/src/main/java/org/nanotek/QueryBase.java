package org.nanotek;

import java.util.List;

public class QueryBase<K extends QueryBase<K>> implements IdBase<K, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5003256051133071281L;

	public String id; 
	
	public List<?> results;
	
	public QueryBase() {
	}

	public QueryBase(String id, List<?> results) {
		super();
		this.id = id;
		this.results = results;
	}

	public String getId() {
		return id;
	}

	public List<?> getResults() {
		return results;
	}

}
