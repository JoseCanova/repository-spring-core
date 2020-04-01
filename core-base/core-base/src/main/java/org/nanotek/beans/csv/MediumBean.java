package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.Medium;
import org.nanotek.entities.BaseMediumBean;
import org.nanotek.proxy.ProxyBase;

public class MediumBean
<K extends BaseBean<MediumBean<K>,Medium<?>>> 
extends ProxyBase<MediumBean<K>,Medium<?>>
implements BaseMediumBean<MediumBean<K>> {


	private static final long serialVersionUID = -8141072962299778762L;


//	public Long mediumId;
//	
//	public Long release; 
//	
//	public Long position;
//	
//	public Long format; 
//	
//	public String name; 
//	
//	public String editsPending; 
//	
//	public String lastUpdated; 
//	
//	public Integer trackCount; 
	
	

	public MediumBean() {
		super(castClass());
	}


	private static Class<? extends Medium<?>> castClass() {
		return (Class<? extends Medium<?>>) Medium.class.asSubclass(Medium.class);
	}


	public MediumBean(Class<? extends Medium<?>> class1) {
		super(class1);
	}

}
