package org.nanotek.opencsv.base;

import org.nanotek.AnyBase;
import org.nanotek.BaseEntity;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.collections.BaseMap;
import org.springframework.beans.factory.InitializingBean;

public class CsvMapBean 
<K extends AnyBase<K,D>, D extends BaseBean<D,ID> , ID extends BaseEntity<?,?>>
extends BaseMap<K,D,ID> implements InitializingBean  {

	private static final long serialVersionUID = -6244931489632185774L;
	
	protected D baseBean;
	
	public CsvMapBean() {
		super();	
		immutable = null;
	}
	
	public CsvMapBean(ID k) {
		super();
		immutable = k;
	}
	
	public CsvMapBean(D baseBean , ID baseEntity) {
		super();
		immutable = baseEntity;
		this.baseBean = baseBean;
	}
	
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
	}
	
	public static void main (String[] args) {
			CsvMapBean mapBean = new CsvMapBean(new ArtistBean () , new Artist());
			mapBean.afterPropertiesSet();
	}
}	
