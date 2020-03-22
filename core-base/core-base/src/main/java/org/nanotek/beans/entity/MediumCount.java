package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value="MediumCount")
public class MediumCount extends LongCountBase{

	private static final long serialVersionUID = 4332038107455870423L;
	
	@NotNull
	@OneToOne(optional=false, mappedBy="trackCount")
	private Medium medium;

	public MediumCount() {
	}

	public MediumCount(@NotNull Long count , @NotNull Medium medium) {
		super(count);
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
		MediumCount other = (MediumCount) obj;
		if (medium == null) {
			if (other.medium != null)
				return false;
		} else if (!medium.equals(other.medium))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MediumCount [medium=" + medium + ", count=" + count + ", id=" + id + "]";
	}
	
}
