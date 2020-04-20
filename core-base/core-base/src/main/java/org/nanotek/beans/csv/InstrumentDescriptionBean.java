package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaType;
import org.nanotek.beans.entity.InstrumentDescription;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.entities.BaseInstrumentDescriptionBean;
import org.nanotek.proxy.ProxyBase;

public class InstrumentDescriptionBean<K extends BaseBean<InstrumentDescriptionBean<K>,InstrumentDescription<?>>> 
extends ProxyBase<InstrumentDescriptionBean<K>,InstrumentDescription<?>>
implements BaseInstrumentDescriptionBean<InstrumentDescriptionBean<K>>{

	private static final long serialVersionUID = -8448752427285481821L;

	public <ID extends AreaType<?>> InstrumentDescriptionBean() {
		super(castClass());
	}
	
	
	@SuppressWarnings("unchecked")
	private static Class<? extends InstrumentDescription<?>> castClass() {
		return (Class<? extends InstrumentDescription<?>>) 
				InstrumentDescription.class.
				asSubclass(InstrumentDescription.class);
	}

	public InstrumentDescriptionBean(Class<InstrumentDescription<?>> class1) {
		super(class1);
	}

}