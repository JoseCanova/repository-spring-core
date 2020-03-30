package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.entities.BaseAreaBeginDateBean;
import org.nanotek.proxy.ProxyBase;


/*
 * 
 */
public class AreaBeginDateBean 
<K extends BaseBean<AreaBeginDateBean<K>,AreaBeginDate<?>>> 
extends ProxyBase<AreaBeginDateBean<K>,AreaBeginDate<?>>
implements BaseAreaBeginDateBean<AreaBeginDateBean<K>> {

	private static final long serialVersionUID = 8578601319977342826L;

	public AreaBeginDateBean(Class<AreaBeginDate<?>>id) {
		super(id);
	}

	public static void main(String[] args) {
		AreaBeginDateBean bean = new AreaBeginDateBean(AreaBeginDate.class);
		bean.setBeginYear(2000);
		System.out.println(bean.getBeginYear());
		bean.setBeginMonth(2000);
		System.out.println(bean.getBeginMonth());
		bean.setBeginDay(2000);
		System.out.println(bean.getBeginDay());
		System.out.println(bean.getId());
	}
	
}
