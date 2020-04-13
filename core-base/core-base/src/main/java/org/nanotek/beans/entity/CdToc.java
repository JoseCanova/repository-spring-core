package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.nanotek.annotations.BrainzKey;

@SuppressWarnings("serial")
@Entity
@Table(name="cd_toc")
public class CdToc<K extends CdToc<K>> extends BrainzBaseEntity<K> {

	private Long cdtocId;
	private String discId; 
	private String freedbId; 
	private Long trackCount;
	
	@BrainzKey(entityClass = CdToc.class, pathName = "cdtocId")
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
		
}
