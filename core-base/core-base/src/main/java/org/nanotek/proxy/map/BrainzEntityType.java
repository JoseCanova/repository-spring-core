package org.nanotek.proxy.map;

import javax.persistence.metamodel.EntityType;

public class BrainzEntityType {

	public BrainzEntityType() {
	}

	@SuppressWarnings("unchecked")
	public static <K extends EntityType<X>,X> EntityType<X> with(EntityType entityType) {
		return entityType;
	}

}
