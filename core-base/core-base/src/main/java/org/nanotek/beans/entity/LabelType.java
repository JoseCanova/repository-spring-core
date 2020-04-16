package org.nanotek.beans.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value ="LabelType" )
public class LabelType<E extends LabelType<E>> extends BaseType<E> {

	private static final long serialVersionUID = 8043624354365641677L;

	@OneToMany(mappedBy = "labelType")
	public Set<Label<?>> label;
	
	
	public LabelType() {
		super();
	}

	public LabelType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}

	public LabelType(@NotNull Long typeId) {
		super(typeId);
	}

	public LabelType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}

	public Set<Label<?>> getLabel() {
		return label;
	}

	public void setLabel(Set<Label<?>> label) {
		this.label = label;
	}
	
}
