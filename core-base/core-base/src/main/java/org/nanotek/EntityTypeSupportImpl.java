package org.nanotek;

import org.hibernate.metamodel.model.domain.internal.EntityTypeImpl;

public class EntityTypeSupportImpl<X> implements EntityTypeSupport<EntityTypeSupportImpl<X>, X> {

	public EntityTypeImpl<X> delegateEntityType;
	
	public EntityTypeSupportImpl() {
	}
	
	public EntityTypeSupportImpl(EntityTypeImpl<X> delegateEntityType) {
		super();
		this.delegateEntityType = delegateEntityType;
	}

	@Override
	public EntityTypeImpl<X> getEntityType() {
		return delegateEntityType;
	}

}
