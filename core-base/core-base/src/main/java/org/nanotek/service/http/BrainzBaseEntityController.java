package org.nanotek.service.http;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.repository.jpa.BrainzBaseEntityRepository;

public interface BrainzBaseEntityController<B extends BrainzBaseEntity<B>> extends BrainzBaseEntityRepository<B>{
}