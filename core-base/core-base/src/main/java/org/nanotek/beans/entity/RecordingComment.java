package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.nanotek.entities.BaseRecordingCommentEntity;
import org.nanotek.entities.MutableCommentEntity;

@Entity
@DiscriminatorValue(value = "RecordingComment")
public class RecordingComment<K extends RecordingComment<K>> 
extends CommentBase<K> implements  
BaseRecordingCommentEntity<K> , 
MutableCommentEntity<String> {

	private static final long serialVersionUID = 282575265480502546L;

	public RecordingComment() {
		super();
	}

	public RecordingComment(@NotBlank String comment) {
		super(comment);
	}
	
}
