package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistCreditedNameBean;
import org.nanotek.beans.entity.ArtistCreditedName;
import org.nanotek.beans.entity.ArtistCreditedNamePosition;
import org.nanotek.entities.immutables.ArtistCreditNameIdEntity;
import org.nanotek.entities.immutables.ArtistCreditNameJoinPhraseEntity;
import org.nanotek.entities.immutables.ArtistCreditedNameEntity;

public interface BaseArtistCreditedNameBean
<K extends BaseBean<K,ArtistCreditedName<?>>> extends 
Base<K>,
BaseBean<K,ArtistCreditedName<?>>,
MutableArtistCreditedNameEntity<String>,
MutatleArtistCreditNameIdEntity<Long>,
MutableArtistEntity<BaseArtistBean<?>>,
MutableArtistCreditEntity<BaseArtistCreditBean<?>>,
MutableArtistCreditNamePositionEntity<BaseArtistCreditedNamePositionBean<?>>,
MutableArtistCreditNameJoinPhraseEntity<String>,
MutablePositionEntity<Long>
{
	
	
	@Override
	default void setArtistCreditedName(String k) {
		write(MutableArtistCreditedNameEntity.class,k);
	}

	@Override
	default String getArtistCreditedName() {
		return read(ArtistCreditedNameEntity.class).map(n->String.class.cast(n)).orElse("");
	}
	
	@Override
	default Long getArtistCreditNameId() {
		return read(ArtistCreditNameIdEntity.class).map(v->Long.class.cast(v)).orElse(Long.MIN_VALUE);
	}
	
	@Override
	default void setArtistCreditNameId(Long k) {
		write(MutatleArtistCreditNameIdEntity.class,k);
	}
	
	default void setArtistId(Long id) {
		getArtist().setArtistId(id);
	}
	
	default Long getArtistId() {
		return getArtist().getArtistId();
	}
	
	default void setArtistCreditId(Long id) {
		getArtistCredit().setArtistCreditId(id);
	}
	
	default Long getArtistCreditId() {
		return getArtistCredit().getArtistCreditId();
	}
	
	@Override
	default String getArtistCreditNameJoinPhrase() {
		return read(ArtistCreditNameJoinPhraseEntity.class).map(s->String.class.cast(s)).orElse("");
	}
	
	@Override
	default void setArtistCreditNameJoinPhrase(String k) {
		String joinPhrase = isEmpty(k)? "" : k;
		write(MutableArtistCreditNameJoinPhraseEntity.class,joinPhrase);
	}
	
	default boolean isEmpty(String k) { 
		return k == null || k.isEmpty();
	}
	
	default Long getPosition() {
		return getArtistCreditNamePosition().getPosition();
	}
	
	default void setPosition(Long k) {
		getArtistCreditNamePosition().setPosition(k);
	}
	
	
	public static  void main (String [] args) {
		ArtistCreditedNameBean<?> bean = new ArtistCreditedNameBean<>();
		bean.setArtistCreditedName("this is a name");
		System.out.println(bean.getArtistCreditedName());
		bean.setArtistCreditId(1000L);
		System.out.println("aritst credit id" + bean.getArtistCreditId());
		bean.setArtistCreditNameJoinPhrase("this a join phrase");
		System.out.println(bean.getArtistCreditNameJoinPhrase());
		bean.setArtistId(1002L);
		System.out.println("artist id " + bean.getArtistId());
	}
	
}
