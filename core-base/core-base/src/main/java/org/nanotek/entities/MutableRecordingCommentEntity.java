package org.nanotek.entities;

import org.nanotek.entities.immutables.RecordingCommentEntity;

public interface MutableRecordingCommentEntity<K> extends RecordingCommentEntity<K>{
	void setRecordingComment(K k);
}
