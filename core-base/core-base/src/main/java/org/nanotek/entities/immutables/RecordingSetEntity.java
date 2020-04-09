package org.nanotek.entities.immutables;

import java.io.Serializable;
import java.util.Set;

public interface RecordingSetEntity<T> {
Set<T> getRecordings();
}
