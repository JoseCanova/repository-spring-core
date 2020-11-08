package org.nanotek.spring.data.elastic.repository;

import java.util.List;

import org.nanotek.beans.entity.ArtistCredit;

public interface ElasticArtistCreditRepository<A extends ArtistCredit<A>> extends ElasticBrainzBaseRepository<A>{
	
	List<A> findByArtistCreditName(String artistCreditName);
	List<A> findByArtistCreditNameLike(String artistCreditName);

	@Override
	default Class<?> getBrainzClass() {
		return ArtistCredit.class;
	}
}
