package org.nanotek.config;

import java.util.HashMap;
import java.util.Map;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "nanotek") // Binds to 'nanotek' prefix in application.yml
public class CsvFileConfigurations<T extends BaseMap<S,P,M> , 
S  extends AnyBase<S,String> , 
P   extends AnyBase<P,Integer> , 
M extends BaseBean<?,?>> {

    // This map will hold all your named CSV configurations (e.g., 'artist', 'area', 'labeltype' etc.)
    private Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> csvConfigs = new HashMap<>();

    public Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> getCsvConfigs() {
        return csvConfigs;
    }

    public void setCsvConfigs(Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> csvConfigs) {
        this.csvConfigs = csvConfigs;
    }
}