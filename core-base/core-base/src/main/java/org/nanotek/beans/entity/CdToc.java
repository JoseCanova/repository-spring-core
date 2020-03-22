package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.LongBase;

@SuppressWarnings("serial")
@Entity
@Table(name="cd_toc")
public class CdToc implements LongBase<CdToc> {

	private Long id;
	private Long cdtocId;
	private String discId; 
	private String freedbId; 
	private Long trackCount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCdtocId() {
		return cdtocId;
	}
	public void setCdtocId(Long cdtocId) {
		this.cdtocId = cdtocId;
	}
	public String getDiscId() {
		return discId;
	}
	public void setDiscId(String discId) {
		this.discId = discId;
	}
	public String getFreedbId() {
		return freedbId;
	}
	public void setFreedbId(String freedbId) {
		this.freedbId = freedbId;
	}
	public Long getTrackCount() {
		return trackCount;
	}
	public void setTrackCount(Long trackCount) {
		this.trackCount = trackCount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdtocId == null) ? 0 : cdtocId.hashCode());
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
		CdToc other = (CdToc) obj;
		if (cdtocId == null) {
			if (other.cdtocId != null)
				return false;
		} else if (!cdtocId.equals(other.cdtocId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CdToc [id=" + id + ", cdtocId=" + cdtocId + ", discId="
				+ discId + ", freedbId=" + freedbId + ", trackCount="
				+ trackCount + "]";
	} 
	
}
