package org.nanotek.beans.entity.search;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.entities.MutableResultRankEntity;
import org.nanotek.entities.MutableSearchResultEntity;
import org.nanotek.entities.ResultBaseEntity;

@MappedSuperclass
public class ResultBase
<K extends ResultBase<K>> 
extends SequenceLongBase<K, Long>
implements ResultBaseEntity<K>,
MutableSearchResultEntity<Long>,
MutableResultRankEntity<Float>{

	private static final long serialVersionUID = 8378900415235703087L;
	
	@NotNull(groups = PrePersistValidationGroup.class)
	@Column(nullable=false, name="search_result")
	public Long searchResult;
	
	@NotNull(groups = PrePersistValidationGroup.class)
	@Column(nullable=false, name="rank")
	public Float resultRank;

	public ResultBase() {
	}

	public Long getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(Long searchResult) {
		this.searchResult = searchResult;
	}

	public Float getResultRank() {
		return resultRank;
	}

	public void setResultRank(Float resultRank) {
		this.resultRank = resultRank;
	}
}
