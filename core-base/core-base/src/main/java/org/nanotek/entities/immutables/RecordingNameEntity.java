package org.nanotek.entities.immutables;

import org.nanotek.Nameable;

public interface RecordingNameEntity<K> extends Nameable<K>{
K getRecordingName();
}
