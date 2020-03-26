package org.nanotek.beans.entity;

import java.util.UUID;

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



	public ReleaseAliasType(@NotNull Long typeId) {
		super(typeId);
		// TODO Auto-generated constructor stub
	}



	public ReleaseAliasType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
		// TODO Auto-generated constructor stub
	}



	public ReleaseAliasType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
		// TODO Auto-generated constructor stub
	}



}
