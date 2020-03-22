package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseCommentBaseEntity;

@MappedSuperclass
public class CommentBase<K extends CommentBase<K>> 
extends BrainzBaseEntity<K>  
implements BaseCommentBaseEntity<K>{

	private static final long serialVersionUID = -3239637365262870832L;
	
	@NotNull
	@Column(name="comment", columnDefinition = "VARCHAR NOT NULL"  , nullable=false)
	protected String comment;

	public CommentBase() {
	}
	
	public CommentBase(@NotBlank String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String k) {
		this.comment = k;
	}

}
