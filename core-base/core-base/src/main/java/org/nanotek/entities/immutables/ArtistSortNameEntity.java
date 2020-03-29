package org.nanotek.entities.immutables;

import java.io.Serializable;

import org.nanotek.beans.entity.ArtistSortName;
import org.nanotek.entities.MutableSortNameEntity;

public interface ArtistSortNameEntity<K extends Serializable>  {

	K getArtistSortName();
}
