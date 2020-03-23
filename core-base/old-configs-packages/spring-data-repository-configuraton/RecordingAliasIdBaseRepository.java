package org.nanotek.repository.jpa;

import java.util.Optional;

import org.nanotek.beans.entity.RecordingAlias;
import org.nanotek.entities.MutableRecordingAliasIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingAliasIdBaseRepository<K extends RecordingAlias<K>> 
extends BrainzBaseRepository<K> {
	Optional<MutableRecordingAliasIdEntity<Long>> findByRecordingAliasId(Long recordingAliasId);
}
