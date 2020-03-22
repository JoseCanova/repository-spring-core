package org.nanotek.beans.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableLengthEntity;

@MappedSuperclass
public abstract class LengthyBase<K extends LengthyBase<K,L>,L extends Serializable> 
extends BrainzBaseEntity<K> implements MutableLengthEntity<L>{
	private static final long serialVersionUID = 266384916289553935L;
//TODO: Check the question mark by what it implements.
	
	@NotNull
	@Column(name="length" , nullable=false)
	protected  L length;
	
	public LengthyBase() {
	}

	@Override
	public void setLength(L length) {
		this.length = length;
	}
	
	@Override
	public L getLength() {
		return length;
	}
	
}
