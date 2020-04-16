package org.nanotek.entities;

public interface MutableWorkIdEntity<K> extends WorkIdEntity<K>{
void setWorkId(K workId);
}
