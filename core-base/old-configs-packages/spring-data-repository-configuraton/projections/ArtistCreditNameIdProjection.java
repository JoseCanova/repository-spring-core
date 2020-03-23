package org.nanotek.repository.jpa.projections;

import java.io.Serializable;
import java.util.Optional;

import org.nanotek.entities.MutatbleArtistCreditNameIdEntity;

public interface ArtistCreditNameIdProjection<T extends MutatbleArtistCreditNameIdEntity<TID> , TID extends Serializable> {
				Optional<T>	findByArtistCreditNameId(TID id);
}
