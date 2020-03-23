package org.nanotek.controller.csv;

import org.nanotek.base.maps.ArtistBeanBaseMap;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.service.parser.ArtistBeanParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.bytecode.opencsv.bean.CsvToBean;

@RestController
@RequestMapping("/csv/artist_bean")
public class ArtistBeanCsvController  extends CsvController <ArtistBeanBaseMap , ArtistBean, ArtistBeanParser> {

	@Autowired
	@Qualifier("ArtistBeanParser")
	private ArtistBeanParser baseParser;
	
	@Autowired
	@Qualifier("ArtistBeanCsvToBean")
	private CsvToBean<ArtistBean> csvToBean;
	
	public CsvToBean<ArtistBean> getCsvToBean() {
		return csvToBean;
	}

	@Override
	public ArtistBeanParser getBaseParser() {
		return baseParser;
	}

	@Override
	public ArtistBeanBaseMap getBaseMap() {
		return baseParser.getArtistBaseMap();
	}
	
}