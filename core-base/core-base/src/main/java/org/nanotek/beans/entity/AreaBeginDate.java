package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.nanotek.entities.AreaBeginDateEntity;
import org.nanotek.entities.MutableBeginDateEntity;
import org.nanotek.entities.MutableBeginDayEntity;
import org.nanotek.entities.MutableBeginMonthEntity;
import org.nanotek.entities.MutableBeginYearEntity;

@Entity
@DiscriminatorValue("AreaBeginDate")
public class AreaBeginDate<K extends AreaBeginDate<K>> extends BeginDateBase<K> implements  
AreaBeginDateEntity<K>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>{

	private static final long serialVersionUID = 4640549011512215583L;

	public AreaBeginDate() {
	}

	public AreaBeginDate(Integer year) {
		super(year);
	}

	public AreaBeginDate(Integer year, Integer month) {
		super(year, month);
	}

	public AreaBeginDate(Integer year, Integer month, Integer day) {
		super(year, month, day);
	}

	
}
