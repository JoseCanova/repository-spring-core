package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
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
import javax.validation.groups.Default;

import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseAreaEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@Table(name="area" , 
uniqueConstraints= {
		@UniqueConstraint(name="uk_area_id",columnNames={"area_id"})
		})
public class Area
<K extends Area<K>> extends BrainzBaseEntity<K> implements  BaseAreaEntity<K>
															{

	private static final long serialVersionUID = -7073321340141567106L;
	
	@NotNull(groups = {CsvValidationGroup.class,Default.class})
	@Column(name="area_id",nullable=false)
	public Long areaId; 
	
	@NotNull(groups = {Default.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid;
	
		
	@NotNull
	@ManyToOne(optional=false, fetch = FetchType.LAZY )
	public AreaType<?> areaType; 
	
	@OneToOne(cascade = CascadeType.ALL , optional = true , fetch = FetchType.LAZY)
	@JoinTable(
			  name = "area_begin_date_join", 
			  joinColumns = @JoinColumn(name = "area_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public AreaBeginDate<?> areaBeginDate; 
	
	@OneToOne(cascade = CascadeType.ALL , optional = true , fetch = FetchType.LAZY)
	@JoinTable(
			  name = "area_end_date_join", 
			  joinColumns = @JoinColumn(name = "area_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id") )
	public AreaEndDate<?> areaEndDate;
	

	@OneToOne(cascade = CascadeType.ALL , optional = true , fetch = FetchType.LAZY)
	@JoinTable(
			  name = "area_comment_join", 
			  joinColumns = @JoinColumn(name = "area_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id") )
	public AreaComment<?> areaComment;
	
	
	
	/**
	 * 
	 */
	@NotNull(groups = {Default.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String areaName;


	@Override
	public String getAreaName() {
		return areaName;
	}

	@Override
	public void setAreaName(String k) {
		this.areaName = k;
	}
	
	public Area() {
		areaBeginDate = new AreaBeginDate<>();
		areaEndDate = new AreaEndDate<>();
		areaType = new AreaType<>();
		areaComment = new AreaComment<>(this);
	}
	
	public Area(@NotNull Long id, @NotBlank String name, @NotBlank  UUID gid , @NotNull  AreaType<?> type) {
		this.areaName = name;
		this.gid = gid;
		this.areaId = id;
		this.areaType = type;
		areaBeginDate = new AreaBeginDate<>();
		areaEndDate = new AreaEndDate<>();
		areaType = new AreaType();
		areaComment = new AreaComment();
	}

	@Override
	@BrainzKey(entityClass = Area.class, pathName = "areaId")
	public Long getAreaId() {
		return areaId;
	}

	@Override
	public AreaComment<?> getAreaComment() {
		return areaComment;
	}

	@Override
	public AreaBeginDate<?> getAreaBeginDate() {
		return areaBeginDate;
	}

	@Override
	public AreaEndDate<?> getAreaEndDate() {
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

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public AreaType<?> getAreaType() {
		return areaType;
	}

	public void setAreaType(AreaType<?> areaType) {
		this.areaType = areaType;
	}

}