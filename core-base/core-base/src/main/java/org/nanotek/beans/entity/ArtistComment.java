package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.BaseArtistCommentEntity;
import org.nanotek.entities.MutableArtistEntity;

@Entity
@DiscriminatorValue(value = "ArtistComment")
public class ArtistComment<E extends ArtistComment<E>> extends CommentBase<E> implements BaseArtistCommentEntity<E>,
																						 MutableArtistEntity<Artist<?>> {
	private static final long serialVersionUID = 2608408556126104972L;

	@OneToOne(mappedBy = "artistComment")
	public Artist<?> artist;

	public ArtistComment() {}
	
	public ArtistComment(@NotBlank String comment, Artist<?> artist) {
		super(comment);
	}
	
	public ArtistComment(@NotBlank String comment) {
		super(comment);
	}

	public Artist<?> getArtist() {
		return artist;
	}

	public void setArtist(Artist<?> artist) {
		this.artist = artist;
	}
	
}
