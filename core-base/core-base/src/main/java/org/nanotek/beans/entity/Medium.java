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

import org.nanotek.entities.MutableMediumCountEntity;
import org.nanotek.entities.MutableNameEntity;

@Entity
@Table(uniqueConstraints= {
		@UniqueConstraint(name="uk_medium_id",columnNames={"medium_id"})
		})
public class Medium<K extends Medium<K>> extends BrainzBaseEntity<K> 
implements MutableNameEntity<String>,MutableMediumCountEntity<MediumCount<?>>{

	private static final long serialVersionUID = 6669274101742169443L;
	
	@Column(name="medium_id")
	public Long mediumId;

	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;

	
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
	public MediumPosition position;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY , optional = false)
	@JoinColumn(name="release_id")
	public Release<?> release;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="medium_format_id")
	public MediumFormat<?> format;

	public Medium() {}

	
	public Medium(@NotBlank String name) {
		this.name = name;
	}


	public MediumPosition getPosition() {
		return position;
	}

	public void setPosition(MediumPosition position) {
		this.position = position;
	}

	public Release<?> getRelease() {
		return release;
	}

	public void setRelease(Release<?> release) {
		this.release = release;
	}


	public Long getMediumId() {
		return mediumId;
	}


	public void setMediumId(Long mediumId) {
		this.mediumId = mediumId;
	}


	public MediumFormat<?> getFormat() {
		return format;
	}


	public void setFormat(MediumFormat<?> format) {
		this.format = format;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public MediumCount<?> getMediumCount() {
		return mediumCount;
	}


	public void setMediumCount(MediumCount<?> mediumCount) {
		this.mediumCount = mediumCount;
	}

}
