package org.nanotek.beans.entity.search;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.nanotek.entities.MutableSearchRequestEntity;

@Entity
@Table(name="search_result")
public class SearchResult
<K extends SearchResult<K>> extends ResultBase<K>
implements MutableSearchRequestEntity<SearchRequest<?>>{

	private static final long serialVersionUID = -718208630403197969L;
	
	@ManyToOne(optional = false)
	SearchRequest<?> searchRequest; 
	
	
	public SearchResult() {
	}


	public SearchRequest<?> getSearchRequest() {
		return searchRequest;
	}


	public void setSearchRequest(SearchRequest<?> searchRequest) {
		this.searchRequest = searchRequest;
	}
	
}
