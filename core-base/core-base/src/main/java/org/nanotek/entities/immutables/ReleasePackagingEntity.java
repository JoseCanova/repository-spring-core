package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface ReleasePackagingEntity<T extends Serializable> {
T getReleasePackaging();
}
