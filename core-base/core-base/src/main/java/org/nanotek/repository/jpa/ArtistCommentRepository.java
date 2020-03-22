package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ArtistComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistCommentRepository  extends JpaRepository <ArtistComment ,Long> {

}
