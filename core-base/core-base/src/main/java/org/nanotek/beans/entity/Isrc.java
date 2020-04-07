package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.annotations.BrainzKey;

@SuppressWarnings("serial")
@Entity
@Table(name="isrc")
public class Isrc<K extends Isrc<K>> extends BrainzBaseEntity<K> {
	

	@Column (name="ISRC_ID" , insertable=true)
	private Long isrcId; 
	@Column (name="RECORDING_ID" , insertable=true )
	private Long recording; 
	@Column (name="SOURCE" , insertable=true , length=255)
	private String source; 
	@Column (name="ISRC" , insertable=true , length=255)
	private String isrc; 
	
	
	public Isrc() {}

	@BrainzKey(entityClass = Isrc.class, pathName = "isrcId")
	public Long getIsrcId() {
		return isrcId;
	}

	public void setIsrcId(Long isrcId) {
		this.isrcId = isrcId;
	}

	public Long getRecording() {
		return recording;
	}
	
	public void setRecording(Long recording) {
		this.recording = recording;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

}
