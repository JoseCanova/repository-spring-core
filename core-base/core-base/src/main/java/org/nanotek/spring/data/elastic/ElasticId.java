package org.nanotek.spring.data.elastic;

import org.springframework.data.annotation.Id;

public class ElasticId<K extends ElasticId<K>> implements ElasticBase<K,String>{

	private static final long serialVersionUID = -8002326154956802898L;
	
	@Id
	private String id;
	
	public ElasticId() {
		super();
	}
	
	public ElasticId(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 
	
}
