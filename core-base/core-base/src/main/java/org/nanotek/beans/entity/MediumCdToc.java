package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.nanotek.LongBase;

@SuppressWarnings("serial")
@Entity
@Table(name="medium_cd_toc")
public class MediumCdToc<K extends MediumCdToc<K>> extends BrainzBaseEntity<K>{


	private Long mediumCdTocId; 
	private Long medium; 
	private Long cdToc; 
	
	public Long getMedium() {
		return medium;
	}

	public void setMedium(Long medium) {
		this.medium = medium;
	}

	public Long getCdToc() {
		return cdToc;
	}

	public void setCdToc(Long cdToc) {
		this.cdToc = cdToc;
	}


	public Long getMediumCdTocId() {
		return mediumCdTocId;
	}

	public void setMediumCdTocId(Long mediumCdTocId) {
		this.mediumCdTocId = mediumCdTocId;
	}

}
