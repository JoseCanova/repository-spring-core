package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.opencsv.CsvBaseBean;


public class AreaBeginDateBean 
<K extends BaseBean<AreaBeginDateBean<K>,AreaBeginDate<?>>> 
extends CsvBaseBean<AreaBeginDateBean<K>,AreaBeginDate<?>>{

	public AreaBeginDateBean(Class<AreaBeginDate<?>>id) {
		super(id);
	}

}
