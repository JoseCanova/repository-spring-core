package org.nanotek;

import java.io.Serializable;

public abstract class PositionBase<K extends IdBase<K,ID> ,
										ID extends Serializable, P extends PositionableEntityBase<P,K>> implements  ImmutableBase<K,ID> , PositionableEntityBase<P,K> {

	
	private static final long serialVersionUID = 7569528066806744020L;

}
