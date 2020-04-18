package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.Valid;

import org.nanotek.entities.BaseAreaEndDateEntity;
import org.nanotek.entities.MutableEndDayEntity;
import org.nanotek.entities.MutableEndMonthEntity;
import org.nanotek.entities.MutableEndYearEntity;

@Valid
@Entity
@DiscriminatorValue("AreaEndDate")
public class AreaEndDate<K extends AreaEndDate<K>> 
extends EndDateBase<K> implements
BaseAreaEndDateEntity<K>,
MutableEndYearEntity<Integer>,
MutableEndMonthEntity<Integer>,
MutableEndDayEntity<Integer>{

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
