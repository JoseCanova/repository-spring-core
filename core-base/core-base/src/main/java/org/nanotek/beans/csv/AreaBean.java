package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.entities.BaseAreaBean;
import org.nanotek.opencsv.CsvBaseBean;


public class AreaBean
<K extends BaseBean<K,Area<?>>> 
extends CsvBaseBean<Area<?>>
implements BaseAreaBean<K>{

	private static final long serialVersionUID = 1708381486272333902L;
	
	public Long areaId; 
	public String gid; 
	public String name; 
	public Long typeId; 
	public Integer editsPending; 
	public String lastUpdated; 
	public Integer beginYear; 
	public Integer beginMonth; 
	public Integer beginDay; 
	public Integer endYear; 
	public Integer endMonth; 
	public Integer endDay;
	public String ended; 
	public String comment; 

	public AreaBean() {
	}

	public AreaBean(Class<Area<?>>id) {
		super(id);
	}
	

	public static void main(String[] args) {
		AreaBean a = new AreaBean(Area.class);
		a.setComment("This is a comment");
		System.out.println(a.getComment());
	}

	@Override
	public int compareTo(K o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGid(String k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGid() {
		// TODO Auto-generated method stub
		return null;
	}

}
