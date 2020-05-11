package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.ReleaseAliasBeginDate;
import org.nanotek.entities.BaseReleaseAliasBeginDateBean;
import org.nanotek.proxy.ProxyBase;


/*
 * 
 */
public class ReleaseAliasBeginDateBean 
<K extends BaseBean<ReleaseAliasBeginDateBean<K>,ReleaseAliasBeginDate<?>>> 
extends ProxyBase<ReleaseAliasBeginDateBean<K>,ReleaseAliasBeginDate<?>>
implements BaseReleaseAliasBeginDateBean<ReleaseAliasBeginDateBean<K>> {

	private static final long serialVersionUID = 8578601319977342826L;

	public ReleaseAliasBeginDateBean(Class<ReleaseAliasBeginDate<?>>id) {
		super(id);
	}

	public ReleaseAliasBeginDateBean() {
		super(castClass() );
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseAliasBeginDate<?>> castClass() {
		return (Class<? extends ReleaseAliasBeginDate<?>>) 
				ReleaseAliasBeginDate.class.asSubclass(ReleaseAliasBeginDate.class);
	}
	
	
	public static void main(String[] args) {
		ReleaseAliasBeginDateBean bean = new ReleaseAliasBeginDateBean(ReleaseAliasBeginDate.class);
		bean.setBeginYear(2000);
		System.out.println(bean.getBeginYear());
		bean.setBeginMonth(2000);
		System.out.println(bean.getBeginMonth());
		bean.setBeginDay(2000);
		System.out.println(bean.getBeginDay());
		System.out.println(bean.getId());
	}
	
}
