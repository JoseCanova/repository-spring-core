package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseAliasEntity;
import org.nanotek.entities.MutableReleaseAliasEntity;

@Entity
@Table(name = "release_alias")
public class ReleaseAlias<K extends ReleaseAlias<K>> extends LongIdName<K> implements BaseReleaseAliasEntity<K>,
																MutableReleaseAliasEntity
																{

	private static final long serialVersionUID = -4420910201637029585L;
	
	@Column(name="relase_alias_id" , nullable=false)
	public Long releaseAliasId;
	
	@OneToOne(optional=true)
	@JoinTable(
			  name = "release_alias_locale_join", 
			  joinColumns = @JoinColumn(name = "release_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "locale_id",referencedColumnName = "id"))
	public ReleaseAliasLocale releaseAliasLocale;
	
	@NotNull
	@OneToOne
	@JoinTable(
			  name = "release_alias_sortname_join", 
			  joinColumns = @JoinColumn(name = "release_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "sortname_id",referencedColumnName = "id"))
	public ReleaseAliasSortName releaseAliasSortName;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "release_id")
	public Release release; 
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="type_id")
	public ReleaseAliasType releaseAliasType;

    @OneToOne(optional=true)
	@JoinTable(
			  name = "release_alias_begin_date_join", 
			  joinColumns = @JoinColumn(name = "release_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id"))
    public ReleaseAliasBeginDate releaseAliasBeginDate;
    
    @OneToOne(optional=true)
	@JoinTable(
			  name = "release_alias_end_date_join", 
			  joinColumns = @JoinColumn(name = "release_alias_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "date_id",referencedColumnName = "id"))
    public  ReleaseAliasEndDate releaseAliasEndDate;

	public ReleaseAlias() {
	}
	
	public ReleaseAlias(@NotNull Long id , @NotBlank String name) {
		super(name);
		this.releaseAliasId = id;
	}
	
	public ReleaseAlias(@NotNull Long id , @NotBlank String name, @NotNull ReleaseAliasSortName sortName) {
		super(name);
		this.releaseAliasId = id;
		this.releaseAliasSortName = sortName;
	}
	
	public ReleaseAlias(
			@NotNull Long id, 
			@NotBlank String name, 
			ReleaseAliasLocale locale, 
			@NotNull Release release,
			ReleaseAliasType type, 
			@NotNull ReleaseAliasSortName sortName, 
			ReleaseAliasBeginDate beginDate,
			ReleaseAliasEndDate endDate) {
		this.releaseAliasId = id;
		this.name = name;
		this.releaseAliasLocale = locale;
		this.release = release;
		this.releaseAliasType = type;
		this.releaseAliasSortName = sortName;
		this.releaseAliasBeginDate = beginDate;
		this.releaseAliasEndDate = endDate;
	}

	@Override
	public Long getReleaseAliasId() {
		return releaseAliasId;
	}

	@Override
	public ReleaseAliasLocale getReleaseAliasLocale() {
		return releaseAliasLocale;
	}

	@Override
	public ReleaseAliasSortName getReleaseAliasSortName() {
		return releaseAliasSortName;
	}

	@Override
	public Release getRelease() {
		return release;
	}

	@Override
	public ReleaseAliasType getReleaseAliasType() {
		return releaseAliasType;
	}

	@Override
	public ReleaseAliasBeginDate getReleaseAliasBeginDateEntity(ReleaseAliasBeginDate k) {
		return releaseAliasBeginDate;
	}

	@Override
	public ReleaseAliasEndDate getReleaseAliasEndDate() {
		return releaseAliasEndDate;
	}

	@Override
	public void setReleaseAliasEndDate(ReleaseAliasEndDate k) {
		this.releaseAliasEndDate = k;
	}

	@Override
	public void setReleaseAliasBeginDateEntity(ReleaseAliasBeginDate k) {
		this.releaseAliasBeginDate = k;
	}

	@Override
	public void setReleaseAliasType(ReleaseAliasType releaseAliasType) {
		this.releaseAliasType = releaseAliasType;
	}

	@Override
	public void setRelease(Release k) {
		this.release = k;
	}

	@Override
	public void setReleaseAliasSortName(ReleaseAliasSortName releaseAliasSortName) {
		this.releaseAliasSortName = releaseAliasSortName;
	}

	@Override
	public void setReleaseAliasLocale(ReleaseAliasLocale k) {
		this.releaseAliasLocale = k;
	}

	@Override
	public void setReleaseAliasId(Long k) {
			this.releaseAliasId = k;
	}

}
