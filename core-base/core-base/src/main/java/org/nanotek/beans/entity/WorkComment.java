package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.BaseAreaCommentEntity;
import org.nanotek.entities.MutableWorkEntity;

@Entity
@DiscriminatorValue(value = "WorkComment")
public class WorkComment<K extends WorkComment<K>>
extends CommentBase<K> implements  
BaseAreaCommentEntity<K> , 
MutableWorkEntity<Work<?>>
{

	private static final long serialVersionUID = -7518147472884976766L;
	
	@OneToOne(mappedBy = "workComment")
	public Work<?> work;

	public WorkComment() {
		super();
	}

	public WorkComment(@NotBlank String comment) {
		super(comment);
	}

	public Work<?> getWork() {
		return work;
	}

	public void setWork(Work<?> work) {
		this.work = work;
	}
	
	
	
}
