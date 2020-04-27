package org.nanotek.beans.entity.search;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.entities.MutableEntityClassNameEntity;
import org.nanotek.entities.SearchRequestEntity;

@Entity
@Table(name="search_request")
public class SearchRequest<K extends  SearchRequest<K>> 
extends SearchBase<K> 
implements SearchRequestEntity<K>,
MutableEntityClassNameEntity<String>{

	private static final long serialVersionUID = 7602242057341813376L;
	
	@Column(name="entity_class_name" , nullable=false, columnDefinition="VARCHAR NOT NULL")
	public String entityClassName;

	public SearchRequest() {
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entiyClassName) {
		this.entityClassName = entiyClassName;
	}
	
}
