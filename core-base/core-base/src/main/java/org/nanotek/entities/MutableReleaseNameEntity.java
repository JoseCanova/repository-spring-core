package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseNameEntity;

public interface MutableReleaseNameEntity<K> extends ReleaseNameEntity<K>{
 void setReleaseName(K k);
}
