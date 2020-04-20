package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.InstrumentType;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.entities.BaseInstrumentTypeBean;
import org.nanotek.proxy.ProxyBase;

public class InstrumentTypeBean 
<K extends BaseBean<InstrumentTypeBean<K>,InstrumentType<?>>> 
extends ProxyBase<InstrumentTypeBean<K>,InstrumentType<?>>
implements BaseInstrumentTypeBean<InstrumentTypeBean<K>> {

	BaseBaseTypeDescriptionBean<?> baseTypeDescription;
	
	
	public InstrumentTypeBean() {
		super(castClass());
		postConstruct();
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends InstrumentType<?>> castClass() {
		return (Class<? extends InstrumentType<?>>) InstrumentType.class.asSubclass(InstrumentType.class);
	}
	
	
	public InstrumentTypeBean(Class<? extends InstrumentType<?>> class1) {
		super(class1);
		postConstruct();
	}

	private void postConstruct() {
		 baseTypeDescription = new BaseTypeDescriptionBean<>();
	}

	private static final long serialVersionUID = 245355432039730928L;


	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}


	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> baseTypeDescription) {
		this.baseTypeDescription = baseTypeDescription;
	}

//	public Long instrumentTypeId; 
//	
//	public String name; 
//	
//	public Long parent; 
//	
//	public Long childOrder; 
//	
//	public String description; 
//	
//	public String gid; 
	
	
	
}
