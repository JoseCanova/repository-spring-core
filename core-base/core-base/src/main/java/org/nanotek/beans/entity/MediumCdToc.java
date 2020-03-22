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
public class MediumCdToc implements LongBase<MediumCdToc>{


	@Id
	@GeneratedValue(generator="medium_toc_id_seq",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="medium_toc_id_seq" , sequenceName="medium_toc_id_seq")
	private Long id; 
	private Long mediumCdTocId; 
	private Long medium; 
	private Long cdToc; 
	
	@Override
	public Long getId() {
		return id;
	}

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

	public void setId(Long id) {
		this.id = id;
	}


	public Long getMediumCdTocId() {
		return mediumCdTocId;
	}

	public void setMediumCdTocId(Long mediumCdTocId) {
		this.mediumCdTocId = mediumCdTocId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mediumCdTocId == null) ? 0 : mediumCdTocId.hashCode());
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
		MediumCdToc other = (MediumCdToc) obj;
		if (mediumCdTocId == null) {
			if (other.mediumCdTocId != null)
				return false;
		} else if (!mediumCdTocId.equals(other.mediumCdTocId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MediumCdToc [id=" + id + ", mediumCdTocId=" + mediumCdTocId
				+ ", medium=" + medium + ", cdToc=" + cdToc + "]";
	}

}
