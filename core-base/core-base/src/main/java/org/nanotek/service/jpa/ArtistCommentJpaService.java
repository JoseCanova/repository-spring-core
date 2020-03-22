package org.nanotek.service.jpa;

import org.nanotek.beans.entity.ArtistComment;
import org.nanotek.repository.jpa.ArtistCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistCommentJpaService {

	@Autowired
	private ArtistCommentRepository artistCommentRepository;
	
	public ArtistComment<?> save(ArtistComment<?> artistComment) { 
		return artistCommentRepository.save(artistComment);
	}
	
}
