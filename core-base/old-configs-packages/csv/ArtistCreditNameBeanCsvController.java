package org.nanotek.controller.csv;

import org.nanotek.base.maps.ArtistCreditNameBeanBaseMap;
import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.nanotek.service.parser.ArtistCreditNameBeanParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.bytecode.opencsv.bean.CsvToBean;

@RestController
@RequestMapping(path = "/csv/artist_credit_name_bean")
public class ArtistCreditNameBeanCsvController  extends CsvController<ArtistCreditNameBeanBaseMap , ArtistCreditNameBean, ArtistCreditNameBeanParser>{

	@Autowired
	@Qualifier("ArtistCreditNameBeanParser")
	private ArtistCreditNameBeanParser artistParser;

	@Autowired
	@Qualifier("ArtistCreditNameBeanCsvToBean")
	private CsvToBean<ArtistCreditNameBean> csvToBean;

	@Override
	public ArtistCreditNameBeanBaseMap getBaseMap() {
		return artistParser.getArtistCreditNameBeanBaseMap();
	}

	@Override
	public CsvToBean<ArtistCreditNameBean> getCsvToBean() {
		return csvToBean;
	}

	@Override
	public ArtistCreditNameBeanParser getBaseParser() {
		return artistParser;
	}

}
