package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.nanotek.entities.BaseRecordingAliasTypeEntity;

@Entity
@DiscriminatorValue(value="RecordingAliasType")
public class RecordingAliasType<K extends RecordingAliasType<K>> extends BaseType<K> implements BaseRecordingAliasTypeEntity<K> {

	private static final long serialVersionUID = -1922272725479730994L;
	
	public RecordingAliasType() {
	}
	


	public RecordingAliasType(@NotNull Long typeId) {
		super(typeId);
		// TODO Auto-generated constructor stub
	}



	public RecordingAliasType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
		// TODO Auto-generated constructor stub
	}



	public RecordingAliasType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
		// TODO Auto-generated constructor stub
	}



	
}
