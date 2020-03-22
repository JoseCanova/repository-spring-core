package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.nanotek.entities.immutables.LongIdEntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="isrc")
public class Isrc implements LongIdEntityBase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="isrc_id_seq")
	@SequenceGenerator(name = "isrc_id_seq", sequenceName = "isrc_id_seq")
	private Long id; 
	@Column (name="ISRC_ID" , insertable=true)
	private Long isrcId; 
	@Column (name="RECORDING_ID" , insertable=true )
	private Long recording; 
	@Column (name="SOURCE" , insertable=true , length=255)
	private String source; 
	@Column (name="ISRC" , insertable=true , length=255)
	private String isrc; 
	
	
	public Isrc() {}

	@Override
	public Long getId() {
		return id;
	}
	
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

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isrcId == null) ? 0 : isrcId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Isrc other = (Isrc) obj;
		if (isrcId == null) {
			if (other.isrcId != null)
				return false;
		} else if (!isrcId.equals(other.isrcId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Isrc [id=" + id + ", isrcId=" + isrcId + ", recording="
				+ recording + ", source=" + source + ", isrc=" + isrc + "]";
	}

}
