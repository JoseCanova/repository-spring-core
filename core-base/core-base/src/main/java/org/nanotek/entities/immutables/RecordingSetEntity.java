package org.nanotek.entities.immutables;

import java.io.Serializable;
import java.util.Set;

public interface RecordingSetEntity<T extends Serializable> {
Set<T> getRecordings();
}
