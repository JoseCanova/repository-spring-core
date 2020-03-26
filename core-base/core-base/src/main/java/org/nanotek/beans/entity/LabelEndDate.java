package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "LabelEndDate")
public class LabelEndDate<K extends LabelEndDate<K>> extends EndDateBase<K> {

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
