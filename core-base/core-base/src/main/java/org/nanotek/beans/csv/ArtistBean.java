package org.nanotek.beans.csv;

import javax.validation.constraints.NotNull;

import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Artist;

public class ArtistBean
<ID extends BaseEntity<?,?>, K extends ImmutableBase<K,ID>> 
extends CsvBaseBean<ID>
implements BaseBean<K,ID>{


	private static final long serialVersionUID = 2864330060600897052L;

	ID id; 
	
	public ID  getId() { 
		return id;
	}

	@NotNull
	private Long artistId;
	@NotNull
	private String gid;
	@NotNull
	private String name;
	@NotNull
	private String sortName;

	private Integer beginDateYear;
	private Integer beginDateMonth;
	private Integer beginDateDay;
	private Integer endDateYear;
	private Integer endDateMonth;
	private Integer endDateDay;  
	private Long type;
	private Long area;
	private Long gender;
	private String comment;
	private Long editsPending;
	private String lastUpdated;
	private String ended;
	private Long beginArea;
	private Long endArea;


	public ArtistBean() {
		super(Artist.class);
	}

	public ArtistBean(Class<ID> id) {
		super(id);
	}

	
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
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Long getArea() {
		return area;
	}
	public void setArea(Long area) {
		this.area = area;
	}
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getEditsPending() {
		return editsPending;
	}
	public void setEditsPending(Long editsPending) {
		this.editsPending = editsPending;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getEnded() {
		return ended;
	}
	public void setEnded(String ended) {
		this.ended = ended;
	}

	public Integer getEndDateDay() {
		return endDateDay;
	}

	public void setEndDateDay(Integer endDateDay) {
		this.endDateDay = endDateDay;
	}

	public Long getBeginArea() {
		return beginArea;
	}

	public void setBeginArea(Long beginArea) {
		this.beginArea = beginArea;
	}

	public Long getEndArea() {
		return endArea;
	}

	public void setEndArea(Long endArea) {
		this.endArea = endArea;
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

}
