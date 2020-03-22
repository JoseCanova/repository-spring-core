package org.nanotek.tranformer;

import org.nanotek.BaseEntity;
import org.nanotek.EntityBaseTransformer;
import org.nanotek.IdBase;
import org.nanotek.opencsv.base.WrappedBaseBean;

public class WrappedBaseBeanTransformer
<K extends IdBase<K,ID> , ID extends BaseEntity<?,?>> 
extends WrappedBaseBean<ID> 
implements EntityBaseTransformer<K, ID> {

	private ID id;
	
	public WrappedBaseBeanTransformer(@TrasformResult ID id) {
		super(id);
	}

	private static final long serialVersionUID = -8048291218009448615L;

	@Override
	public ID transform(K i) {
		return id;
	}

}
