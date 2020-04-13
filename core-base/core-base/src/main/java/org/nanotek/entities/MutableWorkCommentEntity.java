package org.nanotek.entities;

import org.nanotek.entities.immutables.WorkCommentEntity;

public interface MutableWorkCommentEntity<K> extends WorkCommentEntity<K>{
 void setWorkComment(K k);
}
