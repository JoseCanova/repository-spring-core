package org.nanotek.entities;

import org.nanotek.entities.immutables.ResultRankEntity;

public interface MutableResultRankEntity<K> extends ResultRankEntity<K>{
void setResultRank(K k);
}
