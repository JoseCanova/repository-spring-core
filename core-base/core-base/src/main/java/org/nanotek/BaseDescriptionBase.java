package org.nanotek;

import java.io.Serializable;

import org.nanotek.entities.MutableDescriptionBase;

public interface BaseDescriptionBase
<K extends BaseDescriptionBase<K,D,ID> , D extends Serializable, ID extends Serializable> 
extends MutableDescriptionBase<K , D , ID> {

	D getDescription();
}
