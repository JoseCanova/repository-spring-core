package org.nanotek.entities.metamodel.query.criteria;

import org.hibernate.query.criteria.internal.SelectionImplementor;

public interface BrainzSelectionImplementor
<Z,Y extends SelectionImplementor<Z>> 
extends SelectionImplementor<Z>,BrainzTupleImplementor<Z,Y>{
}
