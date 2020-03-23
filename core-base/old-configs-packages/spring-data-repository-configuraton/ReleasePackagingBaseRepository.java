package org.nanotek.repository.jpa;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.ReleasePackagingBase;

public interface ReleasePackagingBaseRepository <K extends ReleasePackagingBase<ID>,ID extends Serializable>{
	Optional<K> findByReleasePackagingId(@NotNull ID releasePackagingId);
}
