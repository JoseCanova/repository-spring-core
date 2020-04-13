package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value="Gender")
public class Gender<K extends Gender<K>> extends BaseType<K> {

	private static final long serialVersionUID = 1628032460585953186L;

	public Gender() {
	}

	public Gender(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}

	public Gender(@NotNull Long typeId) {
		super(typeId);
	}

	public Gender(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}

}
