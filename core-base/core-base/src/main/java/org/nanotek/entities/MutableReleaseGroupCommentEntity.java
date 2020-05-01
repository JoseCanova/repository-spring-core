package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseGroupCommentEntity;

public interface MutableReleaseGroupCommentEntity<T> extends ReleaseGroupCommentEntity<T>{
void setReleaseGroupComment(T t);
}
