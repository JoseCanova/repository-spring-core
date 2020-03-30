package org.nanotek.entities;

import java.util.UUID;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;

//Used  to simplify implementation of TypeBase persistent entities
public interface BaseTypeBean 
<K extends BaseBean<K,ID> , ID  extends BaseEntity<?,?>> 
extends Base<K>,
BaseBean<K,ID>,
MutableGidEntity<UUID>,
MutableNameEntity<String>,
MutableTypeIdEntity<Long>,
MutableParentEntity<Long>,
MutableChildOrderEntity<Long>,
MutableBaseTypeDescriptionEntity<BaseBaseTypeDescriptionBean<?>>,
MutableDescriptionBaseEntity<String>{

}
