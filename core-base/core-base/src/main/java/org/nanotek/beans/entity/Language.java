package org.nanotek.beans.entity;

import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.nanotek.entities.MutableLanguageEntity;
import org.nanotek.entities.immutables.BaseLanguageEntity;

@SuppressWarnings("rawtypes")
@Entity
@Table(name="language", uniqueConstraints= {
		@UniqueConstraint(name="uk_language_id",columnNames={"language_id"})
		})
public class Language<K extends Language<K>> extends  LongIdName<K>
implements  BaseLanguageEntity<Language>, 
MutableLanguageEntity<Language> {

	private static final long serialVersionUID = 3416483640256915L;
	
	private Stream<Language> immutable;
	
	@NotNull
	@Column(name="language_id")
	private Long languageId;
	
	@NotBlank
	@Size(min = 3 , max = 3)
	@Column(name="isoCode2T" , length=3)
	private String isoCode2T; 
	
	@NotBlank
	@Size(min = 3 , max = 3)
	@Column(name="isoCode2B" , length=3)
	private String isoCode2B; 
	
	@NotBlank
	@Size(min = 2 , max = 2)
	@Column(name="isoCode1" , length=2)
	private String isoCode1; 
	
	@Column(name="frequency")
	private Long frequency;
	
	@NotBlank
	@Size(min = 3 , max = 3)
	@Column(name="isoCode3", length=3)
	private String isoCode3;

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
		immutable = setLanguage(this);
	}

	@Override
	public void setLanguageiId(Long k) {
		this.languageId = k;
	}

	@Override
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
	
	@Override
	public Stream<Language> getLanguage() {
		return immutable;
	}

}
