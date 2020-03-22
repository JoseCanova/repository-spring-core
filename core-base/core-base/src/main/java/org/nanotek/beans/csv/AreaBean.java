package org.nanotek.beans.csv;

import org.nanotek.BaseEntity;
import org.nanotek.IdBase;
import org.nanotek.beans.entity.Area;

public class AreaBean
<ID extends BaseEntity<?,?>, K extends IdBase<K,ID>> 
extends CsvBaseBean<ID>
implements IdBase<K,ID>{

	private static final long serialVersionUID = 1708381486272333902L;
	
	private ID id;

	public ID getId() { 
		return id;
	}
	
	public Long areaId; 
	public String gid; 
	public String name; 
	public Long type; 
	public Integer editsPending; 
	public String lastUpdated; 
	public Integer beginDateYear; 
	public Integer beginDateMonth; 
	public Integer beginDateDay; 
	public Integer endDateYead; 
	public Integer endDateMonth; 
	public Integer endDateDay;
	public String ended; 
	public String comment; 


	public AreaBean() {
		super(Area.class);
	}

	public AreaBean(Class<ID>id) {
		super(id);
	}
	
//	public AreaBean(ID area) { 
//		super(area);
//		this.id =   area;
//	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getType() {
		return type;
	}


	public void setType(Long type) {
		this.type = type;
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


	public Integer getEndDateYead() {
		return endDateYead;
	}


	public void setEndDateYead(Integer endDateYead) {
		this.endDateYead = endDateYead;
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


	public String getEnded() {
		return ended;
	}


	public void setEnded(String ended) {
		this.ended = ended;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Long getAreaId() {
		return areaId;
	}


	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

}
