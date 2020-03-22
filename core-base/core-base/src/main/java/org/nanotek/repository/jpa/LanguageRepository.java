package org.nanotek.repository.jpa;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.beans.entity.Language;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository<K extends Language<K>> 
extends BrainzBaseRepository<K> {
	Optional<K> findByLanguageId(@NotNull Long laguageId);
}
