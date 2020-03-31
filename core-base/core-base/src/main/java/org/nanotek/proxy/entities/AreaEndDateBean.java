package org.nanotek.proxy.entities;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.entities.BaseAreaEndDateBean;
import org.nanotek.proxy.ProxyBase;

public class AreaEndDateBean 
<K extends BaseBean<AreaEndDateBean<K>,AreaEndDate<?>>> 
extends ProxyBase<AreaEndDateBean<K>,AreaEndDate<?>>
implements BaseAreaEndDateBean<AreaEndDateBean<K>>{

	private static final long serialVersionUID = 8768978804455284125L;

	public AreaEndDateBean(Class<AreaEndDate<?>> id) {
		super(id);
	}
	
	public static void main(String[] args) { 
		AreaEndDateBean bean = new AreaEndDateBean(AreaEndDate.class);
		
		bean.setEndYear(2000);
		bean.setEndDay(2000);
		bean.setEndMonth(2000);
		bean.getEndYear();
		bean.getEndMonth();
		bean.getEndDay();
	}

}
