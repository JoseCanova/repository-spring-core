package org.nanotek.repository.jpa.projections;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableRecordingIdEntity;

public interface RecordingBaseProjection<K extends MutableRecordingIdEntity<?>> {
	Optional<K> findByRecordingId(@NotNull K recordingId);
}
