package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.LabelBeginDate;
import org.nanotek.entities.BaseLabelBeginDateBean;
import org.nanotek.proxy.ProxyBase;


/*
 * 
 */
public class LabelBeginDateBean 
<K extends BaseBean<LabelBeginDateBean<K>,LabelBeginDate<?>>> 
extends ProxyBase<LabelBeginDateBean<K>,LabelBeginDate<?>>
implements BaseLabelBeginDateBean<LabelBeginDateBean<K>> {

	private static final long serialVersionUID = 8578601319977342826L;

	public LabelBeginDateBean(Class<LabelBeginDate<?>>id) {
		super(id);
	}

	public LabelBeginDateBean() {
		super(castClass() );
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends LabelBeginDate<?>> castClass() {
		return (Class<? extends LabelBeginDate<?>>) 
				LabelBeginDate.class.asSubclass(LabelBeginDate.class);
	}
	
	
	public static void main(String[] args) {
		LabelBeginDateBean bean = new LabelBeginDateBean(LabelBeginDate.class);
		bean.setBeginYear(2000);
		System.out.println(bean.getBeginYear());
		bean.setBeginMonth(2000);
		System.out.println(bean.getBeginMonth());
		bean.setBeginDay(2000);
		System.out.println(bean.getBeginDay());
		System.out.println(bean.getId());
	}
	
}
