package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseReleaseCommentEntity;
import org.nanotek.entities.MutableCommentEntity;
import org.nanotek.entities.MutableReleaseEntity;

@Entity
@DiscriminatorValue(value="ReleaseComment")
public class ReleaseComment<E extends ReleaseComment<E>> extends CommentBase<E> 
implements BaseReleaseCommentEntity<E>,
MutableCommentEntity<String> , 
MutableReleaseEntity<Release> {

	private static final long serialVersionUID = 4978743759627354208L;
	
	@NotNull
	@OneToOne(mappedBy = "comment" , optional = false , fetch = FetchType.LAZY)
	private Release release;
	
	public ReleaseComment() {
	}

	public ReleaseComment(@NotBlank String comment) {
		super(comment);
	}

	public ReleaseComment(@NotBlank String comment , @NotNull Release release) {
		super();
		this.release = release;
	}

	public Release getRelease() {
		return release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}
	
}
