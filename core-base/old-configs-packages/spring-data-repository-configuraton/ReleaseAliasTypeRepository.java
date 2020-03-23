package org.nanotek.repository.jpa;

import org.nanotek.beans.entity.ReleaseAliasType;
import org.nanotek.repository.jpa.projections.BaseTypeProjection;

public interface ReleaseAliasTypeRepository<K extends ReleaseAliasType<K>> extends 
BrainzBaseRepository<K> , 
BaseTypeProjection<K> {

}
