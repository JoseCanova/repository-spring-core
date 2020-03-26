package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("LabelBeginDate")
public class LabelBeginDate<K extends LabelBeginDate<K>> extends BeginDateBase<K> {

	private static final long serialVersionUID = -226996164338260828L;

	@OneToOne(mappedBy = "labelBeginDate")
	public Label<?> label;

	public LabelBeginDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LabelBeginDate(@NotNull Integer beginYear, @NotNull Integer beginMonth, @NotNull Integer beginDay) {
		super(beginYear, beginMonth, beginDay);
		// TODO Auto-generated constructor stub
	}

	public LabelBeginDate(@NotNull Integer beginYear, @NotNull Integer beginMonth) {
		super(beginYear, beginMonth);
		// TODO Auto-generated constructor stub
	}

	public LabelBeginDate(@NotNull Integer beginYear) {
		super(beginYear);
		// TODO Auto-generated constructor stub
	}

	public Label<?> getLabel() {
		return label;
	}

	public void setLabel(Label<?> label) {
		this.label = label;
	}
	
	
	
}
