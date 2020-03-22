package org.nanotek.controller;

import java.util.List;

import org.nanotek.Result;
import org.nanotek.opencsv.MapColumnStrategy;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class CsvController<P extends CsvBaseProcessor<?,?,R> , R extends Result<?,?>> {

	P processor;
		
	@RequestMapping("/map_config")
    public MapColumnStrategy<? , ?> getBaseMapColumnStrategy() {
        return processor.getBaseParser().getBaseMapColumnStrategy();
    }


    @RequestMapping("/next")
    public R next()  {
    	return processor.next();
    }


    @RequestMapping("/load")
    public List<R> load(@RequestParam(value="count", defaultValue="1") Long count)  {
    	return processor.load(count);
    }	
	
}
