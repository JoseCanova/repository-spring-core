package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "BaseTypeDescription")
public class BaseTypeDescription<K extends BaseTypeDescription<K>> extends DescriptionBase<K>{

	private static final long serialVersionUID = -5502992535337974047L;
	
	@OneToOne(mappedBy = "description")
	protected BaseType<?> baseType;

	public BaseTypeDescription() {
	}

	public BaseTypeDescription(@NotNull String description) {
		super(description);
	}
	
	

}
