package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.nanotek.PrePersistValidationGroup;
import org.nanotek.entities.BaseCommentBaseEntity;
import org.nanotek.entities.MutableCommentEntity;

@MappedSuperclass
public class CommentBase<K extends CommentBase<K>> 
extends BrainzBaseEntity<K>  
implements BaseCommentBaseEntity<K>,
MutableCommentEntity<String>{

	private static final long serialVersionUID = -3239637365262870832L;
	
	@Field(name = "comment" , index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
	@NotBlank(groups = {Default.class,PrePersistValidationGroup.class})
	@Column(name="comment", columnDefinition = "VARCHAR NOT NULL"  , nullable=false)
	public String comment;

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

	@Override
	public String toString() {
		return "CommentBase [comment=" + comment + "]";
	}
	
	

}
