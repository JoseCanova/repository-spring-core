package org.nanotek.controller.entity;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.repository.jpa.BrainzBaseRepository;

public interface EntityNameBaseResponseController<B extends BrainzBaseEntity<B>> extends BrainzBaseRepository<B>{
}
