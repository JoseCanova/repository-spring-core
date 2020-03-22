package org.nanotek.beans.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.nanotek.entities.BaseRecordingAliasTypeEntity;

@Entity
public class RecordingAliasType<K extends RecordingAliasType<K>> extends BaseType<K> implements BaseRecordingAliasTypeEntity {

	private static final long serialVersionUID = -1922272725479730994L;
	
	public RecordingAliasType() {
	}
	
	public RecordingAliasType(@NotBlank @Length(min = 1, max = 50) String gid, @NotNull String name) {
		super(gid, name);
	}

	public RecordingAliasType(@NotNull String name) {
		super(name);
	}

	
}
