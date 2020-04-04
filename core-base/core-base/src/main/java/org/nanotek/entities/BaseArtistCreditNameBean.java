package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.nanotek.beans.entity.ArtistCreditName;
import org.nanotek.beans.entity.ArtistCreditNamePosition;
import org.nanotek.entities.immutables.ArtistCreditNameIdEntity;
import org.nanotek.entities.immutables.ArtistCreditNameJoinPhraseEntity;

public interface BaseArtistCreditNameBean
<K extends BaseBean<K,ArtistCreditName<?>>> extends 
Base<K>,
BaseBean<K,ArtistCreditName<?>>,
MutableNameEntity<String>,
MutatleArtistCreditNameIdEntity<Long>,
MutableArtistEntity<BaseArtistBean<?>>,
MutableArtistCreditEntity<BaseArtistCreditBean<?>>,
MutableArtistCreditNamePositionEntity<BaseArtistCreditNamePositionBean<?>>,
MutableArtistCreditNameJoinPhraseEntity<String>,
MutablePositionEntity<Long>
{
	
	
	@Override
	default void setName(String k) {
		write(MutableNameEntity.class,k);
	}

	@Override
	default String getName() {
		return read(NameEntity.class).map(n->String.class.cast(n)).orElse("");
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
		write(MutableArtistCreditNameJoinPhraseEntity.class,k);
	}
	
	default Long getPosition() {
		return getArtistCreditNamePosition().getPosition();
	}
	
	default void setPosition(Long k) {
		getArtistCreditNamePosition().setPosition(k);
	}
	
	
	public static  void main (String [] args) {
		ArtistCreditNameBean<?> bean = new ArtistCreditNameBean<>();
		bean.setName("this is a name");
		System.out.println(bean.getName());
		bean.setArtistCreditNameId(1000L);
		System.out.println(bean. getArtistCreditNameId());
		System.out.println(bean.getArtistCreditNameId());
		bean.setArtistCreditId(1000L);
		System.out.println(bean.getArtistCreditId());
		bean.setArtistCreditNameJoinPhrase("this a join phrase");
		System.out.println(bean.getArtistCreditNameJoinPhrase());
		bean.setArtistId(1002L);
		System.out.println(bean.getArtistId());
	}
	
}
