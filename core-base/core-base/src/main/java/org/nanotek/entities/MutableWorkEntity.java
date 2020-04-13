package org.nanotek.entities;

public interface MutableWorkEntity<K> extends WorkEntity<K>{
void setWork(K k);
}
