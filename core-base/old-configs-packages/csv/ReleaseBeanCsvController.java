package org.nanotek.controller.csv;

import org.nanotek.base.maps.ReleaseBeanBaseMap;
import org.nanotek.beans.csv.ReleaseBean;
import org.nanotek.service.parser.ReleaseBeanParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.bytecode.opencsv.bean.CsvToBean;

@RestController
@RequestMapping(path = "/csv/release_bean")
public class ReleaseBeanCsvController extends CsvController<ReleaseBeanBaseMap , ReleaseBean, ReleaseBeanParser>{

	@Autowired
	@Qualifier("ReleaseBeanParser")
	private ReleaseBeanParser releaseBeanParser;
	
	@Autowired
	@Qualifier("ReleaseBeanCsvToBean")
	private CsvToBean<ReleaseBean> csvToBean;
	
	@Override
	public ReleaseBeanBaseMap getBaseMap() {
		return releaseBeanParser.getReleaseBeanBaseMap();
	}

	@Override
	public CsvToBean<ReleaseBean> getCsvToBean() {
		return csvToBean;
	}

	@Override
	public ReleaseBeanParser getBaseParser() {
		return releaseBeanParser;
	}
	
}
