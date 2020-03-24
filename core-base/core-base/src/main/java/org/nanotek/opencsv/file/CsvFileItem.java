package org.nanotek.opencsv.file;

import java.util.Optional;

import org.nanotek.AnyBase;
import org.nanotek.collections.BaseMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(value = "config")
public class CsvFileItem 
<S  extends AnyBase<S,String> , P   extends AnyBase<P,Integer>> 
implements InitializingBean{


	public  BaseMap<S,P,?>  baseMap;

	public CsvFileItem(){}
	
	public void afterPropertiesSet() {
		System.out.println("VERIFYING CONFIGURATION MAP");
		Optional.ofNullable(baseMap).ifPresent(m -> m.keySet().forEach(key ->{
			System.out.println(key.getValue().get() + " " +  baseMap.get(key).getValue().get());
		}));

	}
	
	public BaseMap<S, P, ?> getBaseMap() {
		return baseMap;
	}

	public void setBaseMap(BaseMap<S, P, ?> baseMap) {
		this.baseMap = baseMap;
	}
}
