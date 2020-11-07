package org.nanotek.spring.data.elastic;

import org.springframework.data.annotation.Id;

public class ElasticId<K extends ElasticId<K>> implements ElasticBase<K,Long>{

	private static final long serialVersionUID = -8002326154956802898L;
	
	@Id
	private Long id;
	
	public ElasticId() {
		super();
	}
	
	public ElasticId(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 
	
}
