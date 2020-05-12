package org.nanotek.entities;

import org.nanotek.entities.immutables.ReleaseAliasEntity;

public interface MutableReleaseAliasEntity<K> extends ReleaseAliasEntity<K>
{
	void setReleaseAlias( K k );
}
