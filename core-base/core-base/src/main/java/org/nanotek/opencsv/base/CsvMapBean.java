package org.nanotek.opencsv.base;

import org.nanotek.AnyBase;
import org.nanotek.BaseEntity;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.springframework.beans.factory.InitializingBean;

public class CsvMapBean 
<K extends AnyBase<K,D>, D extends BaseBean<D,ID> , ID extends BaseEntity<?,?>>
extends BaseMap<K,D,ID> implements InitializingBean{

	private static final long serialVersionUID = -6244931489632185774L;
	
	public CsvMapBean() {
		
	}
}	
