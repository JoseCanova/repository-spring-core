package org.nanotek.spring.data.elastic.repository;

import java.util.List;

import org.nanotek.beans.entity.ArtistCredit;
import org.springframework.data.repository.Repository;

public interface ElasticArtistCreditRepository extends Repository<ArtistCredit<?>,Long>{
	   List<ArtistCredit<?>> findByArtistCreditName(String artistCreditName);

	   List<ArtistCredit<?>> findByArtistCreditNameLike(String artistCreditName);
}
