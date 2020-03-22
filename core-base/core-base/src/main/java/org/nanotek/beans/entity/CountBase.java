package org.nanotek.beans.entity;

import javax.persistence.MappedSuperclass;

import org.nanotek.Countable;
import org.nanotek.entities.BaseCountBaseEntity;

@MappedSuperclass
public abstract class CountBase<K extends CountBase<K>> extends BrainzBaseEntity<K> implements BaseCountBaseEntity<K>, Countable<Long>{

	private static final long serialVersionUID = 2967357630734834800L;

	public CountBase() {
	}

}
