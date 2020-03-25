package org.nanotek.entities;

import java.io.Serializable;

public interface MutableArtistBeginAreaEntity<K extends Serializable> {
	public void setBeginArea(K k);
	public K getBeginArea();

}
