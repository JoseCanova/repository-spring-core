package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@DiscriminatorValue(value="Gender")
public class Gender<K extends Gender<K>> extends BaseType<K> {

	private static final long serialVersionUID = 1628032460585953186L;

	public Gender() {
	}

	
	public Gender(@NotBlank @Length(min = 1, max = 50) String gid, @NotBlank String name) {
		super(gid, name);
	}


	public Gender(@NotBlank String name) {
		super(name);
	}

}
