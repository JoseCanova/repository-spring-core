package org.nanotek.repository.jpa;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.ReleaseStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseStatusRepository< K extends ReleaseStatus<K>> extends 
BrainzBaseRepository<K> {
	Optional<K> findByReleaseStatusId(@NotNull Long releaseStatusId);
}