package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.entities.BaseAreaBean;
import org.nanotek.opencsv.CsvBaseBean;


public class AreaBean
<K extends BaseBean<AreaBean<K>,Area<?>>> 
extends CsvBaseBean<AreaBean<K>,Area<?>>
implements BaseAreaBean<AreaBean<K>>{

	private static final long serialVersionUID = 1708381486272333902L;
	

	public AreaBean(Class<Area<?>>id) {
		super(id);
	}
	

	public static void main(String[] args) {
		AreaBean a = new AreaBean(Area.class);
		a.setComment("This is a comment");
		a.setAreaId(1000L);
		a.setBeginYear(100);
		System.out.println(a.getComment() + "  " + a.getAreaId());
	}
}
