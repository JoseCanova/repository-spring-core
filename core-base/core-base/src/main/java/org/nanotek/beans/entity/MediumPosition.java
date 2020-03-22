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
	@OneToOne(mappedBy="position" , optional=false)
	private Medium medium;

	public MediumPosition() {
	}

	public MediumPosition(@NotNull Long position , @NotNull Medium medium) {
		super(position);
		this.medium = medium;
	}

	public Medium getMedium() {
		return medium;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((medium == null) ? 0 : medium.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediumPosition other = (MediumPosition) obj;
		if (medium == null) {
			if (other.medium != null)
				return false;
		} else if (!medium.equals(other.medium))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MediumPosition [medium=" + medium + ", position=" + position + ", id=" + id + "]";
	}
	
}
