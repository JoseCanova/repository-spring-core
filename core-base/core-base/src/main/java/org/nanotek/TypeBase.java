package org.nanotek;

import java.io.Serializable;

import org.nanotek.entities.MutableTypeBase;

public interface TypeBase<K extends Serializable> extends MutableTypeBase<K> {
	K getType();
}
