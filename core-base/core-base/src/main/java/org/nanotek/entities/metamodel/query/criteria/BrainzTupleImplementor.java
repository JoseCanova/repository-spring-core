package org.nanotek.entities.metamodel.query.criteria;

import javax.persistence.TupleElement;

import org.hibernate.query.criteria.internal.TupleElementImplementor;
import org.hibernate.query.criteria.internal.expression.AbstractTupleElement;
import org.nanotek.Id;

public interface BrainzTupleImplementor<Z,Y extends TupleElementImplementor<Z>> extends TupleElementImplementor<Z> , Id<Class<Z>> {

	
	@Override
	default Class<Z> getId() {
		return null;
	}
	
	@Override
	default Class<Z> getJavaType() {
		return getId();
	}
	
	Y  getDelegateTupleElement();

}
