package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseIsrcEntity;
import org.nanotek.entities.MutableIsrcEntity;
import org.nanotek.entities.MutableIsrcIdEntity;
import org.nanotek.entities.MutableIsrcSourceEntity;
import org.nanotek.entities.MutableRecordingEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="isrc",
indexes = {
		@Index(name="uk_isrc_id" , columnList= "isrc_id")
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

	@Column (name="isrc_id" , insertable=true)
	private Long isrcId; 
	
	@OneToOne (optional=false)
	@JoinTable(name = "isrc_recording_join",
	joinColumns = {@JoinColumn(name="isrc_id",referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name="recording_id",referencedColumnName = "id")})
	private Recording<?> recording;
	
	@Column (name="source" , insertable=true )
	private Integer isrcSource; 
	
	@NotBlank
	@Size(min=1,max=12)
	@Column (name="isrc" , insertable=true,nullable = false , columnDefinition = " CHAR(12) NOT NULL CHECK (isrc ~ E'^[A-Z]{2}[A-Z0-9]{3}[0-9]{7}$')") 
	private String isrc; 
	
	
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
