package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.MediumCount;
import org.nanotek.entities.BaseMediumCountBean;
import org.nanotek.proxy.ProxyBase;

public class MediumCountBean 
<K extends BaseBean<MediumCountBean<K>,MediumCount<?>>> 
extends ProxyBase<MediumCountBean<K>,MediumCount<?>>
implements BaseMediumCountBean<MediumCountBean<K>>{

	private static final long serialVersionUID = 1673782830917070482L;

	public MediumCountBean() {
		super(castClass());
		postConstructMediumCount();
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends MediumCount<?>> castClass() {
		return (Class<? extends MediumCount<?>>) 
				MediumCount.class.asSubclass(MediumCount.class);
	}
	
	public MediumCountBean(Class<MediumCount<?>>id) {
		super(id);
		postConstructMediumCount();
	}

	private void postConstructMediumCount() {
		
	}

}
