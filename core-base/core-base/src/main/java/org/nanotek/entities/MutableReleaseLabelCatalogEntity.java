package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseLabelCatalogEntity;

public interface MutableReleaseLabelCatalogEntity<K> extends ReleaseLabelCatalogEntity<K>{
void setReleaseLabelCatalog(K k);
}
