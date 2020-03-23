package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.AreaComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaCommentRepository<K extends AreaComment<K>> extends BrainzBaseRepository<K>{
}
