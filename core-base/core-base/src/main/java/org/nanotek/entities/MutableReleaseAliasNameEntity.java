package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseAliasNameEntity;

public interface MutableReleaseAliasNameEntity<T> extends ReleaseAliasNameEntity<T>{
void setReleaseAliasName(String k);
}
