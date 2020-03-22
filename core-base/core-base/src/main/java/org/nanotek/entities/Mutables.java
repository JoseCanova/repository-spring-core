package org.nanotek.entities;

import java.io.Serializable;

import org.nanotek.IdBase;

//TODO: Put all setMethods under the mutable base. remoding the set id method.
public interface Mutables<K extends Mutables<K,ID>,ID extends Serializable>  extends  MutableBase<ID>{
}
