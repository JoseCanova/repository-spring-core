package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.entities.BaseAreaBean;
import org.nanotek.entities.BaseAreaBeginDateBean;
import org.nanotek.opencsv.CsvBaseBean;


public class AreaBean
<K extends BaseBean<AreaBean<K>,Area<?>>> 
extends CsvBaseBean<AreaBean<K>,Area<?>>
implements BaseAreaBean<AreaBean<K>>{

	private static final long serialVersionUID = 1708381486272333902L;
	
	private BaseAreaBeginDateBean<?> areaBeginDate;

	public AreaBean(Class<Area<?>>id) {
		super(id);
		postConstruct();
	}
	

	private void postConstruct() {
		areaBeginDate = new  AreaBeginDateBean(AreaBeginDate.class); 
	}


	public BaseAreaBeginDateBean<?> getAreaBeginDate() {
		return areaBeginDate;
	}


	public void setAreaBeginDate(BaseAreaBeginDateBean<?> areaBeginDate) {
		this.areaBeginDate = areaBeginDate;
	}
	
	public static void main(String[] args) {
		AreaBean a = new AreaBean(Area.class);
		a.setComment("This is a comment");
		a.setAreaId(1000L);
		a.setBeginYear(100);
		a.setBeginDay(100);
		a.getBeginDay();
		System.out.println(a.getComment() + "  " + a.getAreaId());
	}

}
