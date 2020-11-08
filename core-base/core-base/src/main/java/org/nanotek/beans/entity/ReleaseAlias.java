package org.nanotek.beans.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseReleaseAliasEntity;
import org.nanotek.entities.MutableReleaseAliasBeginDateEntity;
import org.nanotek.entities.MutableReleaseAliasEndDateEntity;
import org.nanotek.entities.MutableReleaseAliasIdEntity;
import org.nanotek.entities.MutableReleaseAliasLocaleEntity;
import org.nanotek.entities.MutableReleaseAliasNameEntity;
import org.nanotek.entities.MutableReleaseAliasSortNameEntity;
import org.nanotek.entities.MutableReleaseAliasTypeEntity;
import org.nanotek.entities.MutableReleaseEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid

@Entity
@Table(name = "release_alias",
uniqueConstraints = {@UniqueConstraint(name="uk_release_alias_id", columnNames = {"relase_alias_id"})})
public class ReleaseAlias<K extends ReleaseAlias<K>> extends BrainzBaseEntity<K> implements 
BaseReleaseAliasEntity<K>,
MutableReleaseAliasIdEntity<Long>,
MutableReleaseAliasLocaleEntity<ReleaseAliasLocale<?>>,
MutableReleaseAliasSortNameEntity<ReleaseAliasSortName<?>>,
MutableReleaseEntity<Release<?>>,
MutableReleaseAliasTypeEntity<ReleaseAliasType<?>>,
MutableReleaseAliasBeginDateEntity<ReleaseAliasBeginDate<?>>,
MutableReleaseAliasEndDateEntity<ReleaseAliasEndDate<?>>,
MutableReleaseAliasNameEntity<String>
{

	private static final long serialVersionUID = -4420910201637029585L;
	
	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="relase_alias_id" , nullable=false)
	public Long releaseAliasId;
	
	
	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String releaseAliasName;
	
	
	@OneToOne(optional=true,cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_alias_locale_join", 
			  joinColumns = @JoinColumn(name = "release_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "locale_id",referencedColumnName = "id"))
	public ReleaseAliasLocale<?> releaseAliasLocale;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_alias_sortname_join", 
			  joinColumns = @JoinColumn(name = "release_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "sortname_id",referencedColumnName = "id"))
	public ReleaseAliasSortName<?> releaseAliasSortName;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "release_id")
	public Release<?> release; 
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="type_id")
	public ReleaseAliasType<?> releaseAliasType;

    @OneToOne(optional=true,cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_alias_begin_date_join", 
			  joinColumns = @JoinColumn(name = "release_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id"))
    public ReleaseAliasBeginDate<?> releaseAliasBeginDate;
    
    @OneToOne(optional=true,cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_alias_end_date_join", 
			  joinColumns = @JoinColumn(name = "release_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id"))
    public  ReleaseAliasEndDate<?> releaseAliasEndDate;

	public ReleaseAlias() {
	}
	
	public ReleaseAlias(@NotNull Long id , @NotBlank String name) {
		this.releaseAliasName=name;
		this.releaseAliasId = id;
	}
	
	public ReleaseAlias(@NotNull Long id , @NotBlank String name, @NotNull ReleaseAliasSortName<?> sortName) {
		this.releaseAliasName=name;
		this.releaseAliasId = id;
		this.releaseAliasSortName = sortName;
	}
	
	public ReleaseAlias(
			@NotNull Long id, 
			@NotBlank String name, 
			ReleaseAliasLocale<?> locale, 
			@NotNull Release<?> release,
			ReleaseAliasType<?> type, 
			@NotNull ReleaseAliasSortName<?> sortName, 
			ReleaseAliasBeginDate<?> beginDate,
			ReleaseAliasEndDate<?> endDate) {
		this.releaseAliasId = id;
		this.releaseAliasName = name;
		this.releaseAliasLocale = locale;
		this.release = release;
		this.releaseAliasType = type;
		this.releaseAliasSortName = sortName;
		this.releaseAliasBeginDate = beginDate;
		this.releaseAliasEndDate = endDate;
	}

	@Override
	@BrainzKey(entityClass = ReleaseAlias.class, pathName = "releaseAliasId")
	public Long getReleaseAliasId() {
		return releaseAliasId;
	}

	public String getReleaseAliasName() {
		return releaseAliasName;
	}

	public void setReleaseAliasName(String releaseAliasName) {
		this.releaseAliasName = releaseAliasName;
	}

	public ReleaseAliasLocale<?> getReleaseAliasLocale() {
		return releaseAliasLocale;
	}

	public void setReleaseAliasLocale(ReleaseAliasLocale<?> releaseAliasLocale) {
		this.releaseAliasLocale = releaseAliasLocale;
	}

	public ReleaseAliasSortName<?> getReleaseAliasSortName() {
		return releaseAliasSortName;
	}

	public void setReleaseAliasSortName(ReleaseAliasSortName<?> releaseAliasSortName) {
		this.releaseAliasSortName = releaseAliasSortName;
	}

	public Release<?> getRelease() {
		return release;
	}

	public void setRelease(Release<?> release) {
		this.release = release;
	}

	public ReleaseAliasType<?> getReleaseAliasType() {
		return releaseAliasType;
	}

	public void setReleaseAliasType(ReleaseAliasType<?> releaseAliasType) {
		this.releaseAliasType = releaseAliasType;
	}

	public ReleaseAliasBeginDate<?> getReleaseAliasBeginDate() {
		return releaseAliasBeginDate;
	}

	public void setReleaseAliasBeginDate(ReleaseAliasBeginDate<?> releaseAliasBeginDate) {
		this.releaseAliasBeginDate = releaseAliasBeginDate;
	}

	public ReleaseAliasEndDate<?> getReleaseAliasEndDate() {
		return releaseAliasEndDate;
	}

	public void setReleaseAliasEndDate(ReleaseAliasEndDate<?> releaseAliasEndDate) {
		this.releaseAliasEndDate = releaseAliasEndDate;
	}

	public void setReleaseAliasId(Long releaseAliasId) {
		this.releaseAliasId = releaseAliasId;
	}



}
