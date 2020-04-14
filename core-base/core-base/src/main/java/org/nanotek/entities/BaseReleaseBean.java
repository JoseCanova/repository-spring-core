package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.ReleaseBean;
import org.nanotek.beans.entity.Release;
import org.nanotek.entities.immutables.ReleaseIdEntity;
import org.nanotek.entities.immutables.ReleaseNameEntity;

public interface BaseReleaseBean<K extends BaseBean<K,Release<?>>> 
extends Base<K>,
BaseBean<K,Release<?>>,
MutableReleaseIdEntity<Long>,
MutableGidEntity<UUID>,
MutableReleaseNameEntity<String>,
MutableArtistCreditEntity<BaseArtistCreditBean<?>>,
MutableReleaseGroupEntity<BaseReleaseGroupBean<?>>{

	//	public Long releaseId; 
	//	public String gid; 
	//	public String name;
	//	public Long artistCreditId; 
	//	public Long releaseGroup; 
	//	public Long status; 
	//	public Long  packaging; 
	//	public Long  language; 
	//	public Integer script;
	//	public String barcode; 
	//	public String comment; 
	//	public Integer editsPending; 
	//	public Integer quality; 
	//	public String lastUpdated;

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
