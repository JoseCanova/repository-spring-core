package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.MutableFrequencyEntity;
import org.nanotek.entities.MutableIsoCode1Entity;
import org.nanotek.entities.MutableIsoCode2BEntity;
import org.nanotek.entities.MutableIsoCode2TEntity;
import org.nanotek.entities.MutableIsoCode3Entity;
import org.nanotek.entities.MutableLanguageIdEntity;
import org.nanotek.entities.MutableLanguageNameEntity;
import org.nanotek.entities.immutables.BaseLanguageEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@Table(name="language", 
indexes= {
		@Index(name="index_language_id",columnList="language_id")
		},
uniqueConstraints = {@UniqueConstraint(name="uk_language_id" , columnNames = {"language_id"})})
public class Language<K extends Language<K>> extends  BrainzBaseEntity<K>
implements  BaseLanguageEntity<K>, 
MutableLanguageIdEntity<Long>,
MutableIsoCode2TEntity<String>,
MutableIsoCode2BEntity<String>,
MutableFrequencyEntity<Long>,
MutableIsoCode3Entity<String>,
MutableLanguageNameEntity<String>,
MutableIsoCode1Entity<String>{

	private static final long serialVersionUID = 3416483640256915L;
	
	
	@NotNull(groups = {CsvValidationGroup.class,Default.class,PrePersistValidationGroup.class})
	@Column(name="language_id")
	public Long languageId;
	
//	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Size(min = 3 , max = 3)
	@Column(name="isoCode2T" , length=3)
	public String isoCode2T; 
	
//	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Size(min = 3 , max = 3)
	@Column(name="isoCode2B" , length=3)
	public String isoCode2B; 
	
//	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Size(min = 2 , max = 2)
	@Column(name="isoCode1" , length=2)
	public String isoCode1; 
	
	@Column(name="frequency")
	public Long frequency;
	
//	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Size(min = 3 , max = 3)
	@Column(name="isoCode3", length=3)
	public String isoCode3;

	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Size(min = 1 , max = 100)
	@Column(name="name", length=100 , nullable=false)
	public String languageName;
	
	
	public Language() {}

	public Language(@NotNull Long languageId, @NotBlank @Size(min = 3, max = 3) String isoCode2T,
			@NotBlank @Size(min = 3, max = 3) String isoCode2B, @NotBlank @Size(min = 2, max = 2) String isoCode1,
			Long frequency, @NotBlank @Size(min = 3, max = 3) String isoCode3) {
		super();
		this.languageId = languageId;
		this.isoCode2T = isoCode2T;
		this.isoCode2B = isoCode2B;
		this.isoCode1 = isoCode1;
		this.frequency = frequency;
		this.isoCode3 = isoCode3;
	}

	@Override
	public void setLanguageId(Long k) {
		this.languageId = k;
	}

	@Override
	@BrainzKey(entityClass = Language.class, pathName = "languageId")
	public Long getLanguageId() {
		return languageId;
	}

	@Override
	public void setIsoCode2T(String k) {
			this.isoCode2T = k;
	}

	@Override
	public String getIsoCode2T() {
		return isoCode2T;
	}

	@Override
	public void setIsoCode2B(String k) {
		this.isoCode2B = k;
	}

	@Override
	public String getIsoCode2B() {
		return isoCode2B;
	}

	@Override
	public void setFrequency(Long k) {
		this.frequency = k;
	}

	@Override
	public Long getFrequency() {
		return frequency;
	}

	@Override
	public void setIsoCode3(String k) {
		this.isoCode3 = k;
	}

	@Override
	public String getIsoCode3() {
		return isoCode3;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getIsoCode1() {
		return isoCode1;
	}

	public void setIsoCode1(String isoCode1) {
		this.isoCode1 = isoCode1;
	}
	

}
