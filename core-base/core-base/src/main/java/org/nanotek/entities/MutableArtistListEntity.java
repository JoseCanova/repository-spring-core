package org.nanotek.entities;

import java.io.Serializable;
import java.util.List;

public interface MutableArtistListEntity<T> extends ArtistListEntity<T>{
void setArtists(List<T> artists);
}
