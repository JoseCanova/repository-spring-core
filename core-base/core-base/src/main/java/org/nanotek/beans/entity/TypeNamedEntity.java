package org.nanotek.beans.entity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@MappedSuperclass
public abstract class TypeNamedEntity<E extends TypeNamedEntity<E>> extends LongIdGidName<E> {

	private static final long serialVersionUID = -5235727515178240574L;

	public TypeNamedEntity() {
	}

	public TypeNamedEntity(@NotNull String name) {
		super(name);
	}

	public TypeNamedEntity(@NotBlank @Length(min = 1, max = 50) String gid, @NotBlank String name) {
		super(gid, name);
	}

}
