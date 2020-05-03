package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.ReleaseBean;
import org.nanotek.beans.entity.Release;
import org.nanotek.entities.immutables.ReleaseIdEntity;
import org.nanotek.entities.immutables.ReleaseNameEntity;

public interface BaseReleaseBean
<K extends BaseBean<K,Release<?>>> 
extends Base<K>,
BaseBean<K,Release<?>>,
MutableReleaseIdEntity<Long>,
MutableGidEntity<UUID>,
MutableReleaseNameEntity<String>,
MutableArtistCreditEntity<BaseArtistCreditBean<?>>,
MutableReleaseGroupEntity<BaseReleaseGroupBean<?>>,
MutableReleaseCommentEntity<BaseReleaseCommentBean<?>>,
MutableReleasePackagingEntity<BaseReleasePackagingBean<?>>,
MutableLanguageEntity<BaseLanguageBean<?>>,
MutableReleaseBarCodeEntity<BaseReleaseBarCodeBean<?>>,
MutableReleaseStatusEntity<BaseReleaseStatusBean<?>>{
	
	/**
	 * 
	id                  SERIAL,
    gid                 UUID NOT NULL,
    name                VARCHAR NOT NULL,
    artist_credit       INTEGER NOT NULL, -- references artist_credit.id
    release_group       INTEGER NOT NULL, -- references release_group.id
    status              INTEGER, -- references release_status.id
    packaging           INTEGER, -- references release_packaging.id
    language            INTEGER, -- references language.id
    script              INTEGER, -- references script.id
    barcode             VARCHAR(255),
    comment             VARCHAR(255) NOT NULL DEFAULT '',
    edits_pending       INTEGER NOT NULL DEFAULT 0 CHECK (edits_pending >= 0),
    quality             SMALLINT NOT NULL DEFAULT -1,
    last_updated        TIMESTAMP WITH TIME ZONE DEFAULT NOW()
	 */

	@Override
	default Long getReleaseId() {
		return read(ReleaseIdEntity.class).map(r->Long.class.cast(r)).orElse(Long.MIN_VALUE);
	}

	@Override
	default void setReleaseId(Long k) {
		write(MutableReleaseIdEntity.class,k);
	}

	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(g->UUID.class.cast(g)).orElse(UUID.randomUUID());
	}
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	@Override
	default void setReleaseName(String k) {
		write(MutableReleaseNameEntity.class,k);
	}
	
	@Override
	default String getReleaseName() {
		return read(ReleaseNameEntity.class).map(n->String.class.cast(n)).orElse("");
	}
	
	default Long getArtistCreditId() { 
		return getArtistCredit().getArtistCreditId();
	}
	
	default void setArtistCreditId(Long k) { 
		 getArtistCredit().setArtistCreditId(k);
	}
	
	default void setReleaseGroupId(Long k) { 
		getReleaseGroup().setReleaseGroupId(k);
	}
	
	default Long getReleaseGroupId() { 
		return getReleaseGroup().getReleaseGroupId();
	}
	
	default Long getReleasePackagingId() {
		return getReleasePackaging().getReleasePackagingId();
	}
	
	default void setReleasePackagingId(Long id) { 
	}
	
	default Long getLanguageId() {
		return getLanguage().getLanguageId();
	}
	
	default void setLanguageId(Long id) { 
		getLanguage().setLanguageId(id);
	}
	
	default void setBarCode(String t) { 
		getReleaseBarCode().setBarCode(t);
	}
	
	default String getBarCode() { 
		return getReleaseBarCode().getBarCode();
	}
	
	default void setComment(String c) { 
		getReleaseComment().setComment(c);
	}
	
	default String getComment() {
		return getReleaseComment().getComment();
	}
	
	default void setReleaseStatusId(Long t) { 
		getReleaseStatus().setReleaseStatusId(t);
	}
	
	default Long getReleaseStatusId() {
		return getReleaseStatus().getReleaseStatusId();
	}
	
	
	public static void main(String[] args) { 
		ReleaseBean<?> bean = new ReleaseBean<>();
		bean.setReleaseName("releese name");
		System.out.println(bean.getReleaseName());
		bean.setReleaseId(1000l);
		System.out.println(bean.getReleaseId());
		System.out.println(bean.getGid());
		bean.setArtistCreditId(1000l);
		System.out.println(bean.getArtistCreditId());
	}


}
