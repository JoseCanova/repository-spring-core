package org.nanotek.entities.immutables;

import java.io.Serializable;

public interface ReleaseGroupEntity<T extends Serializable> {
T getReleaseGroup();
}
