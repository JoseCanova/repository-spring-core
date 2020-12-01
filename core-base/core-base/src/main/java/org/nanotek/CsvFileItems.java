package org.nanotek;

import java.util.List;

import org.nanotek.opencsv.file.CsvFileItemConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "fileitem")
@PropertySource("/mb_file_items.yml")
public class CsvFileItems<T> {
	
	List<CsvFileItemConfig<?,?,?>> fileItems;
}
