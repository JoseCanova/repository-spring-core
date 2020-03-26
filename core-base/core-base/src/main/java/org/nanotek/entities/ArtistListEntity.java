package org.nanotek.entities;

import java.io.Serializable;
import java.util.List;

public interface ArtistListEntity<T extends Serializable> {
List<T> getArtists();
}
