package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value="MediumPosition")
public class MediumPosition extends LongPositionBase<MediumPosition> {

	private static final long serialVersionUID = -5847962105120235784L;
	
	@NotNull
	@OneToOne(mappedBy="mediumPosition" , optional=false)
	public Medium<?> medium;

	public MediumPosition() {
	}

	public MediumPosition(@NotNull Long position , @NotNull Medium<?> medium) {
		super(position);
		this.medium = medium;
	}

	public Medium<?> getMedium() {
		return medium;
	}
	
}
