package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.MutableMediumCountEntity;
import org.nanotek.entities.MutableMediumFormatEntity;
import org.nanotek.entities.MutableMediumNameEntity;
import org.nanotek.entities.MutableMediumPositionEntity;

@Entity
@Table(indexes= {
		@Index(name="idx_medium_id",columnList="medium_id")
		})
public class Medium<K extends Medium<K>> 
extends BrainzBaseEntity<K> 
implements 
MutableMediumNameEntity<String>,
MutableMediumCountEntity<MediumCount<?>>,
MutableMediumPositionEntity<MediumPosition>,
MutableMediumFormatEntity<MediumFormat<?>>
{

	private static final long serialVersionUID = 6669274101742169443L;
	
	@Column(name="medium_id")
	public Long mediumId;

	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String mediumName;

	
	@NotNull
	@OneToOne(optional=false)
	@JoinTable(
			  name = "medium_count_join", 
			  joinColumns = @JoinColumn(name = "medium_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "count_id",referencedColumnName = "id"))
	public MediumCount<?> mediumCount; 

	@NotNull
	@OneToOne
	@JoinTable(
			  name = "medium_position_join", 
			  joinColumns = @JoinColumn(name = "medium_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "position_id",referencedColumnName = "id"))
	public MediumPosition mediumPosition;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name="release_id")
	public Release<?> release;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="medium_format_id")
	public MediumFormat<?> mediumFormat;

	public Medium() {}

	
	public Medium(@NotBlank String name) {
		this.mediumName = name;
	}


	public MediumPosition getMediumPosition() {
		return mediumPosition;
	}

	public void setMediumPosition(MediumPosition position) {
		this.mediumPosition = position;
	}

	public Release<?> getRelease() {
		return release;
	}

	public void setRelease(Release<?> release) {
		this.release = release;
	}

	@BrainzKey(entityClass = Medium.class, pathName = "mediumId")
	public Long getMediumId() {
		return mediumId;
	}


	public void setMediumId(Long mediumId) {
		this.mediumId = mediumId;
	}


	public MediumFormat<?> getMediumFormat() {
		return mediumFormat;
	}


	public void setMediumFormat(MediumFormat<?> format) {
		this.mediumFormat = format;
	}


	public String getMediumName() {
		return mediumName;
	}


	public void setMediumName(String name) {
		this.mediumName = name;
	}


	public MediumCount<?> getMediumCount() {
		return mediumCount;
	}


	public void setMediumCount(MediumCount<?> mediumCount) {
		this.mediumCount = mediumCount;
	}

}
