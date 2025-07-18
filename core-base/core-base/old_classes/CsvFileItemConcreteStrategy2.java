package org.nanotek.opencsv;

import javax.inject.Qualifier;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvBaseConfig;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// This bean will be created as a prototype, meaning a new instance
// is created every time it's requested from the Spring context.
@Component
@Scope("prototype")
@ConfigurationProperties(prefix = "nanotek") // Binds to 'nanotek' prefix in application.yml
public class CsvFileItemConcreteStrategy2 <T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>> extends CsvFileItemConcreteStrategy<T,S,P,M>{


    // Spring will use this constructor to inject the CsvBaseConfig
    // when a new prototype instance is requested.
    public CsvFileItemConcreteStrategy2() {
    }

}