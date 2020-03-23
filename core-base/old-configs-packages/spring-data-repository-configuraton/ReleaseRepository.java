package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.Release;
import org.nanotek.repository.jpa.projections.GidBaseProjection;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="ReleaseRepository")
public interface ReleaseRepository<O extends Release<O>>
extends BrainzBaseRepository<O> , 
ReleaseBaseRepository<O,Long>,
NameBaseProjection<O,String>,
GidBaseProjection<O,String>{
}
