package org.nanotek.beans.csv;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.BaseArtistBean;
import org.nanotek.entities.MutableAreaEntity;
import org.nanotek.entities.MutableArtistBeginAreaEntity;
import org.nanotek.entities.MutableArtistEndAreaEntity;
import org.nanotek.entities.MutableArtistIdEntity;
import org.nanotek.entities.MutableBeginDateDayEntity;
import org.nanotek.entities.MutableBeginDateMonthEntity;
import org.nanotek.entities.MutableBeginDateYearEntity;
import org.nanotek.entities.MutableCommentEntity;
import org.nanotek.entities.MutableEndDateDayEntity;
import org.nanotek.entities.MutableEndDateMonthEntity;
import org.nanotek.entities.MutableEndDateYearEntity;
import org.nanotek.entities.MutableGenderEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableNameEntity;
import org.nanotek.entities.MutableTypeEntity;
import org.nanotek.opencsv.CsvBaseBean;

import com.sun.xml.bind.v2.model.core.ID;


/*
 * public class AreaBean
<K extends BaseBean<K,Area<?>>> 
extends CsvBaseBean<Area<?>>
implements BaseAreaBean<K>
 * 
 */


public class ArtistBean
<K extends BaseBean<K,Artist<?>>> 
extends CsvBaseBean<Artist<?>>
implements BaseArtistBean<K>
{


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
		register();
	}

	public ArtistBean(Class<ID> id) {
		super(id);
		register();
	}

	private void register() {
		registerMutable(AreaBean.class);
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
	
	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = this.getClass().cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}
	
	@Override
	public String toString() { 
		return withUUID().toString();
	}
	
	public static void main(String args[]) {
		ArtistBean artistBean = new ArtistBean();
		MutableArtistIdEntity<ArtistBean<?,?>> m = artistBean.getMutableArtistIdEntity();
		if(artistBean.instanceOf(MutableArtistIdEntity.class))
			{
				System.out.println("instance of MutableArtistIdEntity");
			}
		System.out.println(m.toString());
	}
	
}
