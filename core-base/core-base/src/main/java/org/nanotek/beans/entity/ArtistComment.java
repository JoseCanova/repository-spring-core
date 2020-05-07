package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.search.annotations.Indexed;
import org.nanotek.entities.BaseArtistCommentEntity;
import org.nanotek.entities.MutableArtistEntity;

@Valid
@Indexed
@Entity
@DiscriminatorValue(value = "ArtistComment")
public class ArtistComment<E extends ArtistComment<E>> 
extends CommentBase<E> 
implements BaseArtistCommentEntity<E>{
	private static final long serialVersionUID = 2608408556126104972L;

	public ArtistComment() {}
	
	public ArtistComment(@NotBlank String comment, Artist<?> artist) {
		super(comment);
	}
	
	public ArtistComment(@NotBlank String comment) {
		super(comment);
	}

}
