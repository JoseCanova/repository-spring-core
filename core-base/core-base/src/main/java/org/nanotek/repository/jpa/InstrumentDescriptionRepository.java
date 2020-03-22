package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.InstrumentDescription;
import org.nanotek.repository.jpa.projections.BaseDescriptionBaseProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentDescriptionRepository
<I extends InstrumentDescription<I,?>> extends BaseDescriptionBaseProjection<I,String> ,  BrainzBaseRepository<I>{
}
