package org.nanotek.beans.entity.search;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.entities.MutableQueryRequestEntity;
import org.nanotek.entities.SearchBaseEntity;

@MappedSuperclass
public class SearchBase<K extends SearchBase<K>> 
extends SequenceLongBase<K, Long>
implements SearchBaseEntity<K>,
MutableQueryRequestEntity<String>{

	private static final long serialVersionUID = 7980013409133570238L;

	@NotBlank(groups = PrePersistValidationGroup.class)
	@Column(name="query_request" , columnDefinition = "VARCHAR NOT NULL" , nullable = false)
	public String queryRequest;
	
	public SearchBase() {
	}

	public String getQueryRequest() {
		return queryRequest;
	}

	public void setQueryRequest(String queryRequest) {
		this.queryRequest = queryRequest;
	}
	
}
