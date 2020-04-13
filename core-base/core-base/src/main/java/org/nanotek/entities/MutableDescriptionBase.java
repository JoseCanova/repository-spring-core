package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.EntityBase;

public interface MutableDescriptionBase
<K extends MutableDescriptionBase<K,D,ID>, D , ID extends Serializable> 
extends EntityBase<K,ID>{

	void setDescription(D id);

}