package org.nanotek.beans.entity.search;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.nanotek.entities.MutableEntityClassNameEntity;
import org.nanotek.entities.MutableSetSearchResultsEntity;
import org.nanotek.entities.BaseSearchRequestEntity;

@Entity
@Table(name="search_request")
public class SearchRequest<K extends  SearchRequest<K>> 
extends SearchBase<K> 
implements BaseSearchRequestEntity<K>,
MutableEntityClassNameEntity<String>,
MutableSetSearchResultsEntity{

	private static final long serialVersionUID = 7602242057341813376L;
	
	@Column(name="entity_class_name" , nullable=false, columnDefinition="VARCHAR NOT NULL")
	public String entityClassName;
	
	@OneToMany(mappedBy = "searchRequest")
	public Set<SearchResult<?>> searchResults;

	public SearchRequest() {
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entiyClassName) {
		this.entityClassName = entiyClassName;
	}

	public Set<SearchResult<?>> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(Set<SearchResult<?>> searchResults) {
		this.searchResults = searchResults;
	}
	
}
