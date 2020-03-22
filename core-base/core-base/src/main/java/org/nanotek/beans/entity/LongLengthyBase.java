package org.nanotek.beans.entity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;


@MappedSuperclass
public abstract class LongLengthyBase<K extends LengthyBase<K,Long>> extends LengthyBase<K , Long>{

	private static final long serialVersionUID = 4039937284168041782L;

	public LongLengthyBase() {
	}

	public LongLengthyBase(@NotNull Long length) {
		super();
		this.length = length;
	}

}
