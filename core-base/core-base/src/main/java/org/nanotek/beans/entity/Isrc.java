package org.nanotek.beans.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseIsrcEntity;
import org.nanotek.entities.MutableIsrcEntity;
import org.nanotek.entities.MutableIsrcIdEntity;
import org.nanotek.entities.MutableIsrcSourceEntity;
import org.nanotek.entities.MutableRecordingEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@SuppressWarnings("serial")
@Entity
@Table(name="isrc",
indexes = {
		@Index(name="idx_isrc_id" , columnList= "isrc_id")
},
uniqueConstraints = 
{@UniqueConstraint(name="uk_isrc_id",columnNames = {"isrc_id"})})
public class Isrc<K extends Isrc<K>> extends BrainzBaseEntity<K>
implements 
BaseIsrcEntity<K>,
MutableIsrcIdEntity<Long>,
MutableRecordingEntity<Recording<?>>,
MutableIsrcEntity<String>,
MutableIsrcSourceEntity<Integer>
{

	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column (name="isrc_id" , insertable=true)
	public Long isrcId; 
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne (optional=false,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "isrc_recording_join",
	joinColumns = {@JoinColumn(name="isrc_id",referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name="recording_id",referencedColumnName = "id")})
	public Recording<?> recording;
	
	@Column (name="source" , insertable=true )
	public Integer isrcSource; 
	
	@NotBlank(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Size(min=1,max=12)
	@Column (name="isrc" , insertable=true,nullable = false , columnDefinition = " CHAR(12) NOT NULL CHECK (isrc ~ E'^[A-Z]{2}[A-Z0-9]{3}[0-9]{7}$')") 
	public String isrc; 
	
	public Isrc() {}

	@BrainzKey(entityClass = Isrc.class, pathName = "isrcId")
	public Long getIsrcId() {
		return isrcId;
	}

	public void setIsrcId(Long isrcId) {
		this.isrcId = isrcId;
	}

	public Recording<?> getRecording() {
		return recording;
	}

	public void setRecording(Recording<?> recording) {
		this.recording = recording;
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	public Integer getIsrcSource() {
		return isrcSource;
	}

	public void setIsrcSource(Integer isrcSource) {
		this.isrcSource = isrcSource;
	}

}
