package org.nanotek.opencsv;

import org.nanotek.AnyBase;
import org.nanotek.beans.csv.BaseBean;

public class ComparableBase <K extends AnyBase<K,ID>, ID extends BaseBean<ID,?> >
extends CsvBaseBean<ID>{

	private static final long serialVersionUID = 821071618821820675L;

	public ComparableBase() {
	}

}