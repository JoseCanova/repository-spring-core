package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface LongIdNameGidEntityBase<K, N> extends LongIdNameEntityBase<N>  {
	K getGid();
	
}
