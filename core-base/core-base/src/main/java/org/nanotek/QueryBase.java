package org.nanotek;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
@JsonInclude(Include.NON_EMPTY)
public class QueryBase<K extends QueryBase<K>> implements IdBase<K, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5003256051133071281L;

	public String id; 
	
	public List<?> results;
	
	public String className;
	
	
	public QueryBase(String id, List<?> results, String className) {
		super();
		this.id = id;
		this.results = results;
		this.className = className;
	}

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

	public String getClassName() {
		return className;
	}

	@Override
	public String toString() {
		return "QueryBase [id=" + id + ", results=" + results + ", className=" + className + "]";
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setResults(List<?> results) {
		this.results = results;
	}


	public void setClassName(String className) {
		this.className = className;
	}

}
