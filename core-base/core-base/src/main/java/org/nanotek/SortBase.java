package org.nanotek;

import java.io.Serializable;

public interface SortBase<K extends SortBase<K,ID> , ID extends  Serializable> extends EntityBase<K,ID>{
}
