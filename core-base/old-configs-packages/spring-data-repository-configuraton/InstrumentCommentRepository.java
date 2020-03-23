package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.InstrumentComment;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentCommentRepository
<K extends InstrumentComment<K>> extends CommentBaseRepository<K> {
}
