package org.nanotek.controller.csv;

import org.nanotek.base.maps.RecordingBeanBaseMap;
import org.nanotek.beans.csv.RecordingBean;
import org.nanotek.service.parser.RecordingBeanParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.bytecode.opencsv.bean.CsvToBean;

@RestController
@RequestMapping(path = "/csv/recording_bean")
public class RecordingBeanCsvController extends CsvController<RecordingBeanBaseMap , RecordingBean, RecordingBeanParser>{
	
	@Autowired
	@Qualifier("RecordingBeanParser")
	private RecordingBeanParser recordingBeanParser;
	
	@Autowired
	@Qualifier("RercordingBeanCsvToBean")
	private CsvToBean<RecordingBean> csvToBean;

	@Override
	public RecordingBeanBaseMap getBaseMap() {
		return recordingBeanParser.getRecordingBeanBaseMap();
	}

	@Override
	public CsvToBean<RecordingBean> getCsvToBean() {
		return csvToBean;
	}

	@Override
	public RecordingBeanParser getBaseParser() {
		return recordingBeanParser;
	}
	
}
