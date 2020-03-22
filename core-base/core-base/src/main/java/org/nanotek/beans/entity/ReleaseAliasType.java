package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.nanotek.entities.BaseReleaseAliasTypeEntity;

@Entity
@DiscriminatorValue(value="ReleaseAliasType")
public class ReleaseAliasType<K extends ReleaseAliasType<K>> extends BaseType<K> implements BaseReleaseAliasTypeEntity<K>{

	private static final long serialVersionUID = 6615127464602860751L;

	public ReleaseAliasType() {
	}

	public ReleaseAliasType(@NotBlank @Length(min = 1, max = 50) String gid, @NotNull String name) {
		super(gid, name);
	}

	public ReleaseAliasType(@NotNull String name) {
		super(name);
	}

}
