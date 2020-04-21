package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.MediumFormat;
import org.nanotek.entities.BaseMediumFormatBean;
import org.nanotek.proxy.ProxyBase;

public class MediumFormatBean 
<K extends BaseBean<MediumFormatBean<K>,MediumFormat<?>>> 
extends 
ProxyBase<MediumFormatBean<K>,MediumFormat<?>>
implements 
BaseMediumFormatBean<MediumFormatBean<K>>
{


	private static final long serialVersionUID = 6169777855661430422L;


	
//	public Long mediumFormatId;
//	public String name; 
//	public Long parent; 
//	public Long childOrder; 
//	public Integer year; 
//	public String hasDiscIds; 
//	public String description; 
//	public String gid;
	


	public MediumFormatBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends MediumFormat<?>> castClass() {
		return (Class<? extends MediumFormat<?>>) MediumFormat.class.asSubclass(MediumFormat.class);
	}

	public MediumFormatBean(Class<? extends MediumFormat<?>> class1) {
		super(class1);
	}

	
}
