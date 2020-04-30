package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseLabelEndDateEntity;
import org.nanotek.entities.MutableEndDayEntity;
import org.nanotek.entities.MutableEndMonthEntity;
import org.nanotek.entities.MutableEndYearEntity;

@Valid
@Entity
@DiscriminatorValue(value = "LabelEndDate")
public class LabelEndDate<K extends LabelEndDate<K>> 
extends EndDateBase<K>
implements 
BaseLabelEndDateEntity<K>, 
MutableEndYearEntity<Integer>,
MutableEndMonthEntity<Integer>,
MutableEndDayEntity<Integer>{

	private static final long serialVersionUID = 2104580421236405399L;
	
	@OneToOne(mappedBy = "labelEndDate")
	public Label<?> label;

	public LabelEndDate() {
	}

	public LabelEndDate(@NotNull Integer endYear) {
		super(endYear);
	}

	public LabelEndDate(@NotNull @NotNull Integer endYear, @NotNull Integer endMonth) {
		super(endYear, endMonth);
	}

	public LabelEndDate(@NotNull Integer endYear, @NotNull Integer endMonth, @NotNull Integer endDay) {
		super(endYear, endMonth, endDay);
	}

	public Label<?> getLabel() {
		return label;
	}

	public void setLabel(Label<?> label) {
		this.label = label;
	}

}
