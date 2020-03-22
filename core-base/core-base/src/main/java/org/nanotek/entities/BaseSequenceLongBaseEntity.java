package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.BaseEntity;
import org.nanotek.IdBase;
import org.nanotek.PrimaryKey;

public interface BaseSequenceLongBaseEntity<K extends IdBase<K,ID>, ID extends Serializable>  extends BaseEntity<K,ID> , PrimaryKey<K>{

}
