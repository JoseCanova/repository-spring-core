package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.nanotek.entities.BaseAreaEndDateEntity;
import org.nanotek.entities.MutableEndDateEntity;
import org.nanotek.beans.entity.EndDateBase;

@Entity
@DiscriminatorValue("AreaEndDate")
public class AreaEndDate<K extends AreaEndDate<K>> 
extends EndDateBase<K> implements
BaseAreaEndDateEntity<K>,
MutableEndDateEntity<Integer>{

	private static final long serialVersionUID = -8963127006492095677L;

	public AreaEndDate() {
	}

	public AreaEndDate(Integer year) {
		super(year);
	}

	public AreaEndDate(Integer year, Integer month) {
		super(year, month);
	}

	public AreaEndDate(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}

}
