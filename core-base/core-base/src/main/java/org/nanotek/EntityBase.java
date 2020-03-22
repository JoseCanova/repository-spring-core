package org.nanotek;

import java.io.Serializable;

import org.nanotek.entities.Mutables;

public interface EntityBase<K extends EntityBase<K,ID>,ID extends Serializable> extends Mutables<K,ID> , ImmutableBase<K, ID>{
}
