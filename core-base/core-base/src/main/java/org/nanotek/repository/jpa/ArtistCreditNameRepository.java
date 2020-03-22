package org.nanotek.repository.jpa;

import java.util.List;

import org.nanotek.beans.entity.ArtistCreditName;
import org.nanotek.repository.jpa.projections.ArtistCreditNameIdProjection;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistCreditNameRepository<K extends ArtistCreditName<K>> 
extends  BrainzBaseRepository<K>,
ArtistCreditNameIdProjection<K , Long>,
NameBaseProjection<K,String>{
	public List<K> findByArtistCreditId(Long id);
}
