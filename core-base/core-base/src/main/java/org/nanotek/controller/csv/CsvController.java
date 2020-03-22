package org.nanotek.controller.csv;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.nanotek.Base;
import org.nanotek.base.maps.BaseMapColumnStrategy;
import org.nanotek.controller.response.ResponseBase;
import org.nanotek.opencsv.MapColumnStrategy;
import org.nanotek.service.parser.BaseParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.bytecode.opencsv.bean.CsvToBean;

public abstract class CsvController<T extends BaseMapColumnStrategy<I> , I extends Base, P extends BaseParser > {


	public abstract T getBaseMap();

	public abstract CsvToBean<I> getCsvToBean();

	public abstract  P getBaseParser();
	
	@GetMapping("/map_config")
    public MapColumnStrategy<? , ?> mapConfig() {
        return getBaseParser().getBaseMap();
    }

	@GetMapping("/reopenFile")
    public ResponseEntity<String> reopenFile() throws Exception {
         getBaseParser().reopen();
         return new ResponseEntity<String>("reopened", HttpStatus.OK);
    }

    @GetMapping("/next")
    public I next() throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
    	String[] instanceArray = null;
    	I bean = null;
    	if ((instanceArray = getBaseParser().readNext()) !=null) {
    		bean = getCsvToBean().processLine(getBaseMap(), instanceArray);
    	}
    	return bean;
    }


    @GetMapping("/next_entity")
    public ResponseBase<I> nextEntity() {
    	ResponseBase<I> response = null;
    	HttpStatus status = null;
    	I bean = null;
    	try { 
		    	String[] instanceArray = null;
		    	if ((instanceArray = getBaseParser().readNext()) !=null) {
		    		bean = getCsvToBean().processLine(getBaseMap(), instanceArray);
		    	}
		    	if (bean == null)
		    		status = HttpStatus.SERVICE_UNAVAILABLE;
		    	else 
		    		status = HttpStatus.OK;
		    	response = ResponseBase.fromEntity(bean, status);
    	}catch (Exception ex) { 
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    		response = ResponseBase.fromEntity(bean, status);
    	}
    	return response;
    }

    
    @GetMapping("/load")
    public List<I> load(@RequestParam(value="count", defaultValue="1") Long count) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
    	List<I> list = new  ArrayList<>();
    	int i = 0;
    	String[] instanceArray = null;
    	while (i < count && (instanceArray = getBaseParser().readNext()) !=null) {
    		I bean = getCsvToBean().processLine(getBaseMap(), instanceArray);
    		list.add(bean);
    		i++;
    	}
    	return list;
    }	
	
}
