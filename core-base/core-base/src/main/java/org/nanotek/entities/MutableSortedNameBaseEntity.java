package org.nanotek.entities;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.nanotek.entities.immutables.SortNameBaseEntity;

public interface MutableSortedNameBaseEntity<K> extends SortNameBaseEntity<K> {

	void setSortName(@NotBlank K sortName);
	
	default MutableSortedNameBaseEntity<K> withSortName(K sortName){ 
		this.setSortName(sortName);
		return this;
	}
}
