package org.nanotek.opencsv;

import org.nanotek.Base;

import au.com.bytecode.opencsv.bean.CsvToBean;

public class BaseCsvToBean<T extends Base> extends CsvToBean<T> {

	public BaseCsvToBean() {
		super();
	}

}