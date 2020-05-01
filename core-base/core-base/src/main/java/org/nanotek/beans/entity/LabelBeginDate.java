package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseLabelBeginDateEntity;
import org.nanotek.entities.MutableBeginDayEntity;
import org.nanotek.entities.MutableBeginMonthEntity;
import org.nanotek.entities.MutableBeginYearEntity;

@Valid
@Entity
@DiscriminatorValue("LabelBeginDate")
public class LabelBeginDate<K extends LabelBeginDate<K>> 
extends BeginDateBase<K>
implements 
BaseLabelBeginDateEntity<K>,
MutableBeginYearEntity<Integer>,
MutableBeginMonthEntity<Integer>,
MutableBeginDayEntity<Integer>{

	private static final long serialVersionUID = -226996164338260828L;

	@OneToOne(mappedBy = "labelBeginDate")
	public Label<?> label;

	public LabelBeginDate() {
		super();
	}

	public LabelBeginDate(@NotNull Integer beginYear, @NotNull Integer beginMonth, @NotNull Integer beginDay) {
		super(beginYear, beginMonth, beginDay);
	}

	public LabelBeginDate(@NotNull Integer beginYear, @NotNull Integer beginMonth) {
		super(beginYear, beginMonth);
	}

	public LabelBeginDate(@NotNull Integer beginYear) {
		super(beginYear);
	}

	public Label<?> getLabel() {
		return label;
	}

	public void setLabel(Label<?> label) {
		this.label = label;
	}
	
	
	
}
