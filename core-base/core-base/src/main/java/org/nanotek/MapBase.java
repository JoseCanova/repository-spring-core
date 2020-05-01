package org.nanotek;

import java.time.LocalDate;
import java.util.HashMap;

public class MapBase<K extends MapBase<K>> extends HashMap<String,Object> implements IdBase<K,Long>{

	private static final long serialVersionUID = 5667024193213634697L;
	
	private Long id; 
	
	
	public MapBase() {
		id = LocalDate.now().toEpochDay();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
}
