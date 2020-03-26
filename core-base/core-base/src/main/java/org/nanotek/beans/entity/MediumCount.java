package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableMediumEntity;

@Entity
@DiscriminatorValue(value="MediumCount")
public class MediumCount<K extends MediumCount<K>> extends LongCountBase<K>
implements MutableMediumEntity<Medium<?>>
{

	private static final long serialVersionUID = 4332038107455870423L;
	
	@NotNull
	@OneToOne(optional=false, mappedBy="trackCount")
	private Medium<?> medium;

	public MediumCount() {
	}

	public MediumCount(@NotNull Long count , @NotNull Medium medium) {
		super(count);
	}

	public Medium<?> getMedium() {
		return medium;
	}

	public void setMedium(Medium<?> medium) {
		this.medium = medium;
	}
}
