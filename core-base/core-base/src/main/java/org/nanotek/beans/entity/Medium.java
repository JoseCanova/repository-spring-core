package org.nanotek.beans.entity;

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

@Entity
@Table(uniqueConstraints= {
		@UniqueConstraint(name="uk_medium_id",columnNames={"medium_id"})
		})
public class Medium<K extends Medium<K>> extends LongIdName<K>{

	private static final long serialVersionUID = 6669274101742169443L;
	
	@Column(name="medium_id")
	private Long mediumId;

	@NotNull
	@OneToOne(optional=false)
	@JoinTable(
			  name = "medium_count_join", 
			  joinColumns = @JoinColumn(name = "medium_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "position_id",referencedColumnName = "id"))
	private MediumCount trackCount; 

	@NotNull
	@OneToOne
	@JoinTable(
			  name = "medium_position_join", 
			  joinColumns = @JoinColumn(name = "medium_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "position_id",referencedColumnName = "id"))
	private MediumPosition position;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name="release_id")
	private Release release;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="medium_format_id")
	private MediumFormat format;

	public Medium() {}

	
	public Medium(@NotBlank String name) {
		super(name);
	}

	public MediumCount getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(MediumCount trackCount) {
		this.trackCount = trackCount;
	}

	public MediumPosition getPosition() {
		return position;
	}

	public void setPosition(MediumPosition position) {
		this.position = position;
	}

	public Release getRelease() {
		return release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}


	public Long getMediumId() {
		return mediumId;
	}


	public void setMediumId(Long mediumId) {
		this.mediumId = mediumId;
	}


	public MediumFormat getFormat() {
		return format;
	}


	public void setFormat(MediumFormat format) {
		this.format = format;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mediumId == null) ? 0 : mediumId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medium other = (Medium) obj;
		if (mediumId == null) {
			if (other.mediumId != null)
				return false;
		} else if (!mediumId.equals(other.mediumId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Medium [mediumId=" + mediumId + ", trackCount=" + trackCount + ", position=" + position + ", release="
				+ release + ", format=" + format + ", name=" + name + ", id=" + id + "]";
	}

}
