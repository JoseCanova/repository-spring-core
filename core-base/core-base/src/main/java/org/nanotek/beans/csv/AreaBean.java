package org.nanotek.beans.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.opencsv.CsvBaseBean;


public class AreaBean
<K extends BaseBean<K,Area<?>> , ID extends Area<?>> 
extends CsvBaseBean<ID>
implements BaseAreaBean<K,ID>{

	private static final long serialVersionUID = 1708381486272333902L;
	
	public Long areaId; 
	public String gid; 
	public String name; 
	public Long type; 
	public Integer editsPending; 
	public String lastUpdated; 
	public Integer beginYear; 
	public Integer beginMonth; 
	public Integer beginDay; 
	public Integer endYear; 
	public Integer endMonth; 
	public Integer endDay;
	public String ended; 
	public String comment; 


	public AreaBean() {
		super(null);
	}

	public AreaBean(Class<ID>id) {
		super(id);
	}
	
	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = AreaBean.class.cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		set("areaId", areaId);
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
		set("gid",gid);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		set("name",name);
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
		AreaType aType = new AreaType();
		aType.setTypeId(type);
		set("areaType" , aType);
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

	public Integer getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(Integer beginYear) {
		this.beginYear = beginYear;
		Optional.ofNullable(getId().getAreaBeginDate()).ifPresentOrElse(d ->
						{
							d.setBeginYear(beginYear);
						}, () -> {
							AreaBeginDate dt = new AreaBeginDate(beginYear);
							getId().setAreaBeginDate(dt);
						}); 
		
	}

	public Integer getBeginMonth() {
		return beginMonth;
	}

	public void setBeginMonth(Integer beginMonth) {
		this.beginMonth = beginMonth;
		Optional.ofNullable(getId().getAreaBeginDate()).ifPresent(d ->
				{
					d.setBeginMonth(beginMonth);
				});
	}

	public Integer getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(Integer beginDay) {
		this.beginDay = beginDay;
		Optional.ofNullable(getId().getAreaBeginDate()).ifPresent(d ->
		{
			d.setBeginDay(beginDay);
		});
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
		Optional.ofNullable(endYear).ifPresent( e -> {;
			Optional.ofNullable(getId().getAreaEndDate()).ifPresentOrElse(d ->
			{
				d.setEndYear(beginYear);
			}, () -> {
				AreaEndDate dt = new AreaEndDate(endYear);
				getId().setAreaEndDate(dt);
			}); 
		});
	}

	public Integer getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
		Optional.ofNullable(getId().getAreaEndDate()).ifPresent(d ->
		{
			d.setEndMonth(beginMonth);
		});
	}

	public Integer getEndDay() {
		return endDay;
	}

	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
		Optional.ofNullable(getId().getAreaEndDate()).ifPresent(d ->
		{
			d.setEndDay(beginDay);
		});
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
		Optional.ofNullable(getId().getAreaComment()).ifPresentOrElse(c -> c.setComment(comment) ,() ->{
			AreaComment ac = new AreaComment(comment);
			getId().setAreaComment(ac);
		});
	}
	
}
