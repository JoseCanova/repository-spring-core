package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.ReleaseGroupBean;
import org.nanotek.beans.entity.ReleaseGroup;
import org.nanotek.entities.immutables.ReleaseGroupIdEntity;
import org.nanotek.entities.immutables.ReleaseGroupNameEntity;

public interface BaseReleaseGroupBean
<K extends BaseBean<K,ReleaseGroup<?>>> 
extends Base<K>,
BaseBean<K,ReleaseGroup<?>>,
MutableReleaseGroupIdEntity<Long>,
MutableArtistCreditEntity<BaseArtistCreditBean<?>>,
MutableReleaseGroupPrimaryTypeEntity<BaseReleaseGroupPrimaryTypeBean<?>>,
MutableGidEntity<UUID>,
MutableReleaseGroupNameEntity<String>,
MutableReleaseGroupCommentEntity<BaseReleaseGroupCommentBean<?>>{

	@Override
	default void setReleaseGroupId(Long k) {
		write(MutableReleaseGroupIdEntity.class,k);
	}
	
	@Override
	default Long getReleaseGroupId() {
		return read(ReleaseGroupIdEntity.class).map(l->Long.class.cast(l)).orElse(Long.MIN_VALUE);
	}
	
	default void setArtistCreditId(Long k) { 
		getArtistCredit().setArtistCreditId(k);
	}
	
	default Long getArtistCreditId() { 
		return getArtistCredit().getArtistCreditId();
	}
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(u->UUID.class.cast(u)).orElse(UUID.randomUUID());
	}
	
	@Override
	default String getReleaseGroupName() {
		return read(ReleaseGroupNameEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setReleaseGroupName(String k) {
		write(MutableReleaseGroupNameEntity.class,k);
	}
	
	default void setTypeId(Long id) { 
		getReleaseGroupPrimaryType().setTypeId(id);
	}
	
	default Long getTyeId() { 
		return getReleaseGroupPrimaryType().getTypeId();
	}
	
	default String getComment() { 
		return getReleaseGroupComment().getComment();
	}
	
	default void setComment(String k) { 
		getReleaseGroupComment().setComment(k);
	}

	public static void main(String[] args) { 
		ReleaseGroupBean<?> bean = new ReleaseGroupBean<>();
		bean.setReleaseGroupId(1000l);
		System.out.println(bean.getReleaseGroupId());
		bean.setArtistCreditId(1000l);
		System.out.println(bean.getArtistCreditId());
		UUID gid = UUID.randomUUID();
		bean.setGid(gid);
		System.out.println(gid.toString());
		System.out.println(bean.getGid().toString());
		bean.setReleaseGroupName("release group name");
		System.out.println(bean.getReleaseGroupName());
		
	}
}