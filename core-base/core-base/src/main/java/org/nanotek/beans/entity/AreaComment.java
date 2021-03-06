package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.entities.BaseAreaCommentEntity;
import org.nanotek.entities.MutableCommentEntity;

@Valid
@Entity
@DiscriminatorValue(value = "AreaComment")
public class AreaComment<K extends AreaComment<K>> 
extends CommentBase<K> implements  
BaseAreaCommentEntity<K> , 
MutableCommentEntity<String>{

	private static final long serialVersionUID = -68821965634755841L;
	
	@NotNull(groups = Default.class)
	@OneToOne(mappedBy = "areaComment")
	public Area<?> area;

	public AreaComment() {}
	
	public AreaComment(@NotBlank String comment) {
		super(comment);
	}

	public AreaComment(@NotBlank String comment,@NotNull Area<?> area) {
		super(comment);
		this.area = area;
	}

	public AreaComment(Area<?> area2) {
		this.area=area2;
	}

	public Area<?> getArea() {
		return area;
	}

	public void setArea(Area<?> area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "AreaComment [area=" + area + ", comment=" + comment + ", id=" + id + "]";
	}

	@Override
	public String getComment() {
		return comment;
	}
}
