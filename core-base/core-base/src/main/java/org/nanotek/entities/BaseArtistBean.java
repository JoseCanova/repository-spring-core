package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.GidEntity;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.entities.immutables.ArtistIdEntity;

public interface BaseArtistBean
<K extends BaseBean<K,Artist<?>>> extends 
Base<K>,
BaseBean<K,Artist<?>>,
MutableArtistAreaEntity<BaseAreaBean<?>>,
MutableArtistIdEntity<Long>,
MutableArtistSortNameEntity<BaseArtistSortNameBean<?>>,
MutableArtistCommentEntity<BaseArtistCommentBean<?>>,
MutableArtistBeginDateEntity<BaseArtistBeginDateBean<?>>,
MutableArtistEndDateEntity<BaseArtistEndDateBean<?>>,
MutableArtistTypeEntity<BaseArtistTypeBean<?>>,
MutableGenderEntity<BaseGenderBean<?>>,
MutableAreaEntity<BaseAreaBean<?>>,
MutableArtistBeginAreaEntity<BaseAreaBean<?>>,
MutableArtistEndAreaEntity<BaseAreaBean<?>>,
MutableGidEntity<UUID>,MutableNameEntity<String>
{
	
	@Override
	default String getName() {
		return read(NameEntity.class).map(n->String.class.cast(n)).orElse("");
	}
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}
	
	@Override
	default UUID getGid() {
		return read(GidEntity.class).map(v->UUID.class.cast(v)).orElse(UUID.randomUUID());
	}
	
	@Override
	default void setGid(UUID k) {
		write(MutableGidEntity.class,k);
	}
	
	@Override
	default Long getArtistId() {
		return read(ArtistIdEntity.class).map(v->Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setArtistId(Long k) {
		write(MutableArtistIdEntity.class,k);
	}
	
	public static void main (String[] args) {
		ArtistBean artistBean = new ArtistBean();
		
	}
	
}
