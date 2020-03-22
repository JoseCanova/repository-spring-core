package org.nanotek.repository.jpa;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.ArtistCredit;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistCreditRepository<K extends ArtistCredit<K>> 
extends SequenceLongBaseRepository<ArtistCreditRepository<K>,K>, 
NameBaseProjection<K,String> {	
	
	@EntityGraph(value="fetch.ArtistCredit.recordings")
	Optional<K>  findArtistCreditRecordingsById(@NotNull  Long id);
	
	Optional<K> findByArtistCreditId(@NotNull Long artistCreditId);

}
