package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@DiscriminatorValue(value="Gender")
public class Gender<K extends Gender<K>> extends BaseType<K> {

	private static final long serialVersionUID = 1628032460585953186L;

	public Gender() {
	}

	





	public Gender(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
		// TODO Auto-generated constructor stub
	}







	public Gender(@NotNull Long typeId) {
		super(typeId);
		// TODO Auto-generated constructor stub
	}




	public Gender(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
		// TODO Auto-generated constructor stub
	}

}
