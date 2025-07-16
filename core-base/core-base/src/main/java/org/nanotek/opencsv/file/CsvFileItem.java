package org.nanotek.opencsv.file;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;


public abstract class CsvFileItem 
<S  extends AnyBase<S,String> , P   extends AnyBase<P,Integer> , M extends BaseBean<?,?>> 
extends CsvBaseConfig implements InitializingBean{

	private static Logger log = LoggerFactory.getLogger(CsvFileItem.class.getName());
 
    public  Class<M> immutable; 
    
    public  Class<?> elasticref;

	public CsvFileItem(){}
	
	public Class<?> getElasticref() {
		return elasticref;
	}

	public void setElasticref(Class<?> elasticref) {
		this.elasticref = elasticref;
	}

	public void afterPropertiesSet() {
//		log.debug("VERIFYING IMMUTABLE CONFIGURATION");
//		log.debug(immutable.toGenericString());
	}
	
	public Class<M> getImmutable() {
		return immutable;
	}

	public void setImmutable(Class<M> immutable) {
		this.immutable = immutable;
	}

}
