package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ReleaseComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseCommentRepository<K extends ReleaseComment<K>> extends 
BrainzBaseRepository<K> {
}
