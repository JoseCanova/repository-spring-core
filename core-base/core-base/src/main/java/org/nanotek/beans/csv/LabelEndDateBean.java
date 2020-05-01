package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.LabelEndDate;
import org.nanotek.entities.BaseLabelEndDateBean;
import org.nanotek.proxy.ProxyBase;

public class LabelEndDateBean 
<K extends BaseBean<LabelEndDateBean<K>,LabelEndDate<?>>> 
extends ProxyBase<LabelEndDateBean<K>,LabelEndDate<?>>
implements BaseLabelEndDateBean<LabelEndDateBean<K>>{

	private static final long serialVersionUID = 8768978804455284125L;

	public LabelEndDateBean(Class<LabelEndDate<?>> id) {
		super(id);
	}

	public LabelEndDateBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends LabelEndDate<?>> castClass() {
		return (Class<? extends LabelEndDate<?>>) LabelEndDate.class.asSubclass(LabelEndDate.class);
	}

	

	public static void main(String[] args) { 
		LabelEndDateBean bean = new LabelEndDateBean(LabelEndDate.class);
		bean.setEndYear(2000);
		bean.setEndDay(2000);
		bean.setEndMonth(2000);
		bean.getEndYear();
		bean.getEndMonth();
		bean.getEndDay();
		System.out.println(bean.getId().toString());
	}

}
