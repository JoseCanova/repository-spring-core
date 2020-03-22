package org.nanotek.beans.entity;

import javax.persistence.MappedSuperclass;

import org.nanotek.ManagedEntity;

@MappedSuperclass
public class BrainzBaseEntity<K extends BrainzBaseEntity<K>> extends SequenceLongBase<K, Long> implements ManagedEntity<K>{

	private static final long serialVersionUID = -8896061351986644230L;

	public BrainzBaseEntity() {
	}

}
