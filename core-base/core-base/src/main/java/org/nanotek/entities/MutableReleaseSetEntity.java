package org.nanotek.entities;

import java.io.Serializable;
import java.util.Set;

public interface MutableReleaseSetEntity<T> extends ReleaseSetEntity<T>{
void setReleases(Set<T> releases);
}
