package org.nanotek.beans.entity.state;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.nanotek.beans.entity.SequenceLongBase;
import org.nanotek.entities.MutableNameEntity;

@MappedSuperclass
public abstract class BaseState<K extends BaseState<K>> 
extends SequenceLongBase<K, Long> 
implements 
MutableNameEntity<String>{

	private static final long serialVersionUID = 8158418440686655544L;

	@NotBlank
	@Column(length = 100 , nullable = false)
	public String name;
	
	public BaseState() {
	}

	public BaseState(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
