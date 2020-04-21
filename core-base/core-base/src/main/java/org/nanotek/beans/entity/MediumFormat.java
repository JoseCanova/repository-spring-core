package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseMediumFormatEntity;
import org.nanotek.entities.MutableDescriptionEntity;
import org.nanotek.entities.MutableDiscIdEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableMediumFormatIdEntity;
import org.nanotek.entities.MutableMediumFormatNameEntity;
import org.nanotek.entities.MutableParentEntity;
import org.nanotek.entities.MutableYearEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@Table(name="medium_format",
uniqueConstraints = 
{@UniqueConstraint(name="uk_medium_format_id",columnNames = {"medium_format_id"})})
public class MediumFormat<K extends MediumFormat<K>> 
extends BrainzBaseEntity<K> implements 
BaseMediumFormatEntity<K>,
MutableDescriptionEntity<String>,
MutableMediumFormatNameEntity<String>,
MutableYearEntity<Integer>,
MutableMediumFormatIdEntity<Long>,
MutableDiscIdEntity<String>,
MutableParentEntity<Long>,
MutableGidEntity<UUID>
{

	private static final long serialVersionUID = 8104913204474210789L;
	
	@NotNull(groups = {CsvValidationGroup.class,Default.class,PrePersistValidationGroup.class})
	@Column(name="medium_format_id" , insertable=true,nullable=false)
	public Long mediumFormatId;
	
	@NotBlank(groups = {CsvValidationGroup.class,Default.class,PrePersistValidationGroup.class})
	@Column(name = "name" , insertable=true , nullable=false , updatable=true , columnDefinition = "VARCHAR NOT NULL")
	public String mediumFormatName; 
	
	@Column(name = "parent")
	public Long parent;
	
	@Column(name = "year")
	public Integer year; 
	
	@NotBlank(groups = {CsvValidationGroup.class,Default.class,PrePersistValidationGroup.class})
	@Column(name = "hasDiscId", length=6, nullable=false)
	public String discId;
	
	@NotNull(groups = {CsvValidationGroup.class,Default.class,PrePersistValidationGroup.class})
	@Column(name = "gid" , length=50 , insertable=true , nullable=false , updatable=true)
	public UUID gid;
	
	@Column(name="description" , length=4000)
	public String description;
	
	public MediumFormat() { 
		super();
	}
	
	public MediumFormat(
			@NotBlank  String name, 
			Long parent, Integer year, String hasDiscId,
			@NotBlank UUID gid, String description) {
		this.mediumFormatName = name;
		this.parent = parent;
		this.year = year;
		this.discId = hasDiscId;
		this.gid = gid;
		this.description = description;
	}


	public String getMediumFormatName() {
		return mediumFormatName;
	}

	public void setMediumFormatName(String name) {
		this.mediumFormatName = name;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getDiscId() {
		return discId;
	}

	public void setDiscId(String hasDiscId) {
		this.discId = hasDiscId;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@BrainzKey(entityClass = MediumFormat.class, pathName = "mediumFormatId")
	public Long getMediumFormatId() {
		return mediumFormatId;
	}

	public void setMediumFormatId(Long mediumFormatId) {
		this.mediumFormatId = mediumFormatId;
	}

	@Override
	public String toString() {
		return "MediumFormat [mediumFormatId=" + mediumFormatId + ", mediumFormatName=" + mediumFormatName + ", parent="
				+ parent + ", year=" + year + ", discId=" + discId + ", gid=" + gid + ", description=" + description
				+ "]";
	}

	
	
}