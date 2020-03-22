package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.RecordingAlias;

public class RecordingAliasBean 
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{


	private static final long serialVersionUID = 4772995387500387928L;

	private ID id;
	
	public ID getId() {
		return id;
	}
	
	public Long resultId;
	public Long recording; 
	public String name; 
	public String locale; 
	public Integer editsPending; 
	public String lastUpdated; 
	public Long type; 
	public String sortName;
	public Integer beginDateYear ;
	public Integer beginDateMonth;
	public Integer beginDateDay;
	public Integer endDateYear;
	public Integer endDateMonth;
	public Integer endDateDay; 
	public String primaryForLocale;
	public String ended;
	
	
	
	public RecordingAliasBean(Class<ID> id) {
		super(id);
	}

	public RecordingAliasBean() {
		super(RecordingAlias.class);
	}

	public Long getRecording() {
		return recording;
	}

	public void setRecording(Long recording) {
		this.recording = recording;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Integer getEditsPending() {
		return editsPending;
	}

	public void setEditsPending(Integer editsPending) {
		this.editsPending = editsPending;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public Integer getBeginDateYear() {
		return beginDateYear;
	}

	public void setBeginDateYear(Integer beginDateYear) {
		this.beginDateYear = beginDateYear;
	}

	public Integer getBeginDateMonth() {
		return beginDateMonth;
	}

	public void setBeginDateMonth(Integer beginDateMonth) {
		this.beginDateMonth = beginDateMonth;
	}

	public Integer getBeginDateDay() {
		return beginDateDay;
	}

	public void setBeginDateDay(Integer beginDateDay) {
		this.beginDateDay = beginDateDay;
	}

	public Integer getEndDateYear() {
		return endDateYear;
	}

	public void setEndDateYear(Integer endDateYear) {
		this.endDateYear = endDateYear;
	}

	public Integer getEndDateMonth() {
		return endDateMonth;
	}

	public void setEndDateMonth(Integer endDateMonth) {
		this.endDateMonth = endDateMonth;
	}

	public Integer getEndDateDay() {
		return endDateDay;
	}

	public void setEndDateDay(Integer endDateDay) {
		this.endDateDay = endDateDay;
	}

	public String getPrimaryForLocale() {
		return primaryForLocale;
	}

	public void setPrimaryForLocale(String primaryForLocale) {
		this.primaryForLocale = primaryForLocale;
	}

	public String getEnded() {
		return ended;
	}

	public void setEnded(String ended) {
		this.ended = ended;
	}

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

}
