package org.nanotek.tranformer;

import java.util.Optional;

import org.nanotek.BaseEntity;
import org.nanotek.EntityBaseTransformer;
import org.nanotek.ImmutableBase;
import org.nanotek.PredicateBase;
import org.nanotek.opencsv.base.WrappedBaseBean;

public class WrappedBaseBeanTransformer
<K extends ImmutableBase<K,ID> , ID extends BaseEntity<?,?>> 
extends WrappedBaseBean<ID> 
implements EntityBaseTransformer<K, ID> {

	private ID id;
	
	private PredicateBase<K , ID> predicate;
	
	public WrappedBaseBeanTransformer(@TrasformResult ID id) {
		super(id);
	}

	private static final long serialVersionUID = -8048291218009448615L;

	@Override
	public ID transform(K i) {
		return id;
	}

	public Optional<ID> evaluate(K immutable) {
		return predicate.evaluate(immutable);
	}
	
	

}
