package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.BaseReleaseAliasLocaleEntity;

@Entity
@DiscriminatorValue("ReleaseAliasLocale")
public class ReleaseAliasLocale<K extends ReleaseAliasLocale<K>> extends LocaleBase<K> implements 
BaseReleaseAliasLocaleEntity<K>{

	private static final long serialVersionUID = -5609249998157622354L;
	
	@OneToOne(mappedBy = "locale")
	private ReleaseAlias<?> releaseAlias;

	public ReleaseAliasLocale() {
	}
	
	public ReleaseAliasLocale(@NotBlank String locale) {
		super(locale);
	}

	public ReleaseAlias<?> getReleaseAlias() {
		return releaseAlias;
	}

	public void setReleaseAlias(ReleaseAlias<?> releaseAlias) {
		this.releaseAlias = releaseAlias;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
