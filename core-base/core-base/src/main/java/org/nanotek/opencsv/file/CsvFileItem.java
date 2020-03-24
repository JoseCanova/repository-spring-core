package org.nanotek.opencsv.file;

import org.nanotek.AnyBase;
import org.nanotek.beans.csv.BaseBean;
import org.nanotek.collections.BaseMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(value = "config")
public abstract class CsvFileItem 
<S  extends AnyBase<S,String> , P   extends AnyBase<P,Integer> , M extends BaseBean<?,?>> 
extends CsvBaseConfig implements InitializingBean{

	private static Logger log = LoggerFactory.getLogger(CsvFileItem.class.getName());
 
    public  Class<M> immutable; 

	public CsvFileItem(){}
	
	public void afterPropertiesSet() {
		log.debug("VERIFYING IMMUTABLE CONFIGURATION");
		log.debug(immutable.toGenericString());
	}
	
	public Class<M> getImmutable() {
		return immutable;
	}

	public void setImmutable(Class<M> immutable) {
		this.immutable = immutable;
	}

	public  abstract  <T extends BaseMap<?, ?, ?>> T  getBaseMap();
}
