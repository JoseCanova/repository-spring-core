package org.nanotek.entities;

import java.io.Serializable;
import java.util.Set;

public interface ReleaseSetEntity<T> {
Set<T> getReleases();
}
