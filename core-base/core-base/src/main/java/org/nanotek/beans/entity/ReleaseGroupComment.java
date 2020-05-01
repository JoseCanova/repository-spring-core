package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.BaseReleaseGroupCommentEntity;
import org.nanotek.entities.MutableCommentEntity;
import org.nanotek.entities.MutableReleaseGroupEntity;

@Valid
@Entity
@DiscriminatorValue(value = "ReleaseGroupComment")
public class ReleaseGroupComment<K extends ReleaseGroupComment<K>> 
extends CommentBase<K> implements  
BaseReleaseGroupCommentEntity<K> , 
MutableCommentEntity<String>,
MutableReleaseGroupEntity<ReleaseGroup<?>>{

	private static final long serialVersionUID = -68821965634755841L;
	
	@OneToOne(mappedBy = "releaseGroupComment")
	public ReleaseGroup<?> releaseGroup;

	public ReleaseGroupComment() {}
	
	public ReleaseGroupComment(@NotBlank String comment) {
		super(comment);
	}

	public ReleaseGroup<?> getReleaseGroup() {
		return releaseGroup;
	}

	public void setReleaseGroup(ReleaseGroup<?> releaseGroup) {
		this.releaseGroup = releaseGroup;
	}
	
	

}
