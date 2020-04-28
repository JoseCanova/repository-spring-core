package org.nanotek.beans.entity.search;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.entities.MutableSearchResultEntity;
import org.nanotek.entities.ResultBaseEntity;

@MappedSuperclass
public class ResultBase
<K extends ResultBase<K>> 
extends SequenceLongBase<K, Long>
implements ResultBaseEntity<K>,
MutableSearchResultEntity<String>{

	private static final long serialVersionUID = 8378900415235703087L;
	
	@Column(nullable=false, name="search_result",columnDefinition = "VARCHAR NOT NULL")
	public String searchResult;

	public ResultBase() {
	}

	public String getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}
	
}
