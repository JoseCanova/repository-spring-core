package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Instrument;
import org.nanotek.entities.BaseIntrumentBean;
import org.nanotek.proxy.ProxyBase;

public class InstrumentBean
<K extends BaseBean<InstrumentBean<K>,Instrument<?>>> 
extends ProxyBase<InstrumentBean<K>,Instrument<?>>
implements BaseIntrumentBean<InstrumentBean<K>>{


	private static final long serialVersionUID = -6916258778573566572L;
	
	
//	public Long instrumentId; 
//	public String gid; 
//	public String name; 
//	public Long type; 
//	public Integer editsPending; 
//	public String lastUpdatead; 
//	public String comment; 
//	public String description;
	
	public InstrumentBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends Instrument<?>> castClass() {
		return (Class<? extends Instrument<?>>) Instrument.class.asSubclass(Instrument.class);
	}


	public InstrumentBean(Class<? extends Instrument<?>> class1) {
		super(class1);
	}


}
