package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableAreaBeginDateEntity;
import org.nanotek.entities.MutableAreaCommentEntity;
import org.nanotek.entities.MutableAreaEndDateEntity;
import org.nanotek.entities.MutableAreaIdEntity;
import org.nanotek.entities.MutableTypeEntity;

@Entity
@Table(name="area" , 
uniqueConstraints= {
		@UniqueConstraint(name="uk_area_id",columnNames={"area_id"})
		})
public class Area<K extends Area<K>> extends LongIdGidName<K> implements 
															MutableAreaIdEntity<Long>,		
															MutableTypeEntity<AreaType<?>>,
															MutableAreaCommentEntity<AreaComment<?>>,
															MutableAreaBeginDateEntity<AreaBeginDate<?>>,
															MutableAreaEndDateEntity<AreaEndDate<?>>	{

	private static final long serialVersionUID = -7073321340141567106L;
	
	@NotNull
	@Column(name="area_id",nullable=false)
	public Long areaId; 
	
	@NotNull
	@ManyToOne(optional=false, fetch = FetchType.LAZY )
	public AreaType<?> areaType; 
	
	@OneToOne
	@JoinTable(
			  name = "area_begin_date_join", 
			  joinColumns = @JoinColumn(name = "area_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public AreaBeginDate<?> areaBeginDate; 
	
	@OneToOne
	@JoinTable(
			  name = "area_end_date_join", 
			  joinColumns = @JoinColumn(name = "area_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public AreaEndDate<?> areaEndDate;
	

	@OneToOne
	@JoinTable(
			  name = "area_comment_join", 
			  joinColumns = @JoinColumn(name = "area_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id") )
	private AreaComment<?> areaComment;
	
	public Area() {}
	
	public Area(@NotNull Long id, @NotBlank String name, @NotBlank  String gid , @NotNull  AreaType<?> type) {
		this.name = name;
		this.gid = gid;
		this.areaId = id;
		this.areaType = type;
	}

	@Override
	public Long getAreaId() {
		// TODO Auto-generated method stub
		return areaId;
	}

	@Override
	public AreaComment<?> getAreaComment() {
		// TODO Auto-generated method stub
		return areaComment;
	}

	@Override
	public AreaBeginDate<?> getAreaBeginDate() {
		// TODO Auto-generated method stub
		return areaBeginDate;
	}

	@Override
	public AreaEndDate<?> getAreaEndDate() {
		// TODO Auto-generated method stub
		return areaEndDate;
	}

	@Override
	public void setAreaEndDate(AreaEndDate<?> k) {
		this.areaEndDate = k;
	}

	@Override
	public void setAreaBeginDate(AreaBeginDate<?> k) {
		this.areaBeginDate = k;
	}

	@Override
	public void setAreaComment(AreaComment<?> k) {
		this.areaComment = k;
	}

	@Override
	public AreaType<?> getType() {
		return areaType;
	}

	@Override
	public void setAreaId(Long k) {
		this.areaId = k;
	}

	@Override
	public void setType(AreaType<?> k) {
			areaType = k;
	}

}