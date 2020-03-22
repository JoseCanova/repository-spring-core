package org.nanotek.entities.immutables;

import java.io.Serializable;

import org.nanotek.Nameable;

public interface LongIdNameEntityBase<K extends Serializable> extends LongIdEntityBase , Nameable<K>{
}
