package org.nanotek.repository.jpa;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.Area;
import org.nanotek.repository.jpa.projections.NameBaseProjection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="AreaRepository")
public interface AreaRepository<K extends Area<K>> extends  
	BrainzBaseRepository<K>,
	NameBaseProjection<K, String>{
	Optional<Area<?>> findByAreaId(@NotNull Long areaId);
}