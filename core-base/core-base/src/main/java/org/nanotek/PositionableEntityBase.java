package org.nanotek;

import org.nanotek.entities.immutables.PositionEntity;

public interface PositionableEntityBase<P extends PositionableEntityBase<?,T> , T extends IdBase<?,?>> extends PositionEntity<T>{
	
}
