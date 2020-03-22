package org.nanotek;

import java.io.Serializable;

public interface IdBaseMap<T1 extends IdBaseMap<T1 , T2>, T2 extends Serializable> extends ImmutableBase<T1,T2>{

}
