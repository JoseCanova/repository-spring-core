package org.nanotek.opencsv.file;

import org.nanotek.AnyBase;
import org.nanotek.beans.csv.BaseBean;
import org.springframework.beans.factory.InitializingBean;

public abstract class CsvFileItemConfig 
<S  extends AnyBase<S,String> , P   extends AnyBase<P,Integer> , M extends BaseBean<?,?>> 
extends CsvFileItem<S, P, M> implements InitializingBean {

	@Override
	public void afterPropertiesSet(){
		super.afterPropertiesSet();
	}

}
