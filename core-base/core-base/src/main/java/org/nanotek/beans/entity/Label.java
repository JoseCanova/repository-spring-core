package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nanotek.entities.immutables.LongIdEntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="label")
public class Label implements LongIdEntityBase {
	
	@Id
	private Long id; 
	@Column
	private Long labelId;
	@Column
	private String gid; 
	@Column
	private Long name; 
	@Column
	private Long sortName; 
	@Column
	private String type; 
	@Column
	private String labelCode; 
	@Column
	private String country;
	@Column
	private String beginDateYear; 
	@Column
	private String beginDateMonth; 
	@Column
	private String beginDateDay; 
	@Column
	private String endDateYear; 
	@Column
	private String endDateMonth; 
	@Column
	private String endDateDay; 
	@Column
	private String ipiCode;
	
	public Label() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGid() {
		return gid;
	}
	
	public void setGid(String gid) {
		this.gid = gid;
	}
	
	public Long getName() {
		return name;
	}
	
	public void setName(Long name) {
		this.name = name;
	}
	
	public Long getSortName() {
		return sortName;
	}
	
	public void setSortName(Long sortName) {
		this.sortName = sortName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getLabelCode() {
		return labelCode;
	}
	
	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getBeginDateYear() {
		return beginDateYear;
	}
	
	public void setBeginDateYear(String beginDateYear) {
		this.beginDateYear = beginDateYear;
	}
	
	public String getBeginDateMonth() {
		return beginDateMonth;
	}
	
	public void setBeginDateMonth(String beginDateMonth) {
		this.beginDateMonth = beginDateMonth;
	}
	
	public String getBeginDateDay() {
		return beginDateDay;
	}
	
	public void setBeginDateDay(String beginDateDay) {
		this.beginDateDay = beginDateDay;
	}
	
	public String getIpiCode() {
		return ipiCode;
	}
	
	public void setIpiCode(String ipiCode) {
		this.ipiCode = ipiCode;
	}

	public String getEndDateYear() {
		return endDateYear;
	}

	public void setEndDateYear(String endDateYear) {
		this.endDateYear = endDateYear;
	}

	public String getEndDateMonth() {
		return endDateMonth;
	}

	public void setEndDateMonth(String endDateMonth) {
		this.endDateMonth = endDateMonth;
	}

	public String getEndDateDay() {
		return endDateDay;
	}

	public void setEndDateDay(String endDateDay) {
		this.endDateDay = endDateDay;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

}
