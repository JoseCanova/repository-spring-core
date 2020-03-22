package org.nanotek;

import java.io.Serializable;

import org.nanotek.EntityBase;

public abstract class HolderBaseBean<K extends EntityBase<K,ID>,ID extends Serializable>   {

	public HolderBaseBean() {
	}
}
