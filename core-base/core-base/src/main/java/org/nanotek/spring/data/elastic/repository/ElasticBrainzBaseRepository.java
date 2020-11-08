package org.nanotek.spring.data.elastic.repository;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.springframework.data.repository.Repository;

public interface ElasticBrainzBaseRepository<B extends BrainzBaseEntity<B>> extends Repository<B, Long> {
		Class<?> getBrainzClass();
}
