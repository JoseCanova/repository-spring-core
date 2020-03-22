package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.CommentBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentBaseRepository<C extends CommentBase<C>> 
extends BrainzBaseRepository<C> {
}
