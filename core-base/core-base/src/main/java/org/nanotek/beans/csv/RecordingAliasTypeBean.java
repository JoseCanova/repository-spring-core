package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.RecordingAliasType;
import org.nanotek.entities.BaseBaseTypeDescriptionBean;
import org.nanotek.entities.BaseRecordingAliasTypeBean;
import org.nanotek.proxy.ProxyBase;

public class RecordingAliasTypeBean 
<K extends BaseBean<RecordingAliasTypeBean<K>,RecordingAliasType<?>>> 
extends ProxyBase<RecordingAliasTypeBean<K>,RecordingAliasType<?>>
implements BaseRecordingAliasTypeBean<RecordingAliasTypeBean<K>>{

	private static final long serialVersionUID = 2632943505939712312L;
	
	BaseBaseTypeDescriptionBean<?> baseTypeDescription;

	@SuppressWarnings("unchecked")
	private static Class<? extends RecordingAliasType<?>> castClass() {
		return (Class<? extends RecordingAliasType<?>>) 
				RecordingAliasType.class.asSubclass(RecordingAliasType.class);
	}
	
	
	public RecordingAliasTypeBean() {
		super(castClass());
		postConstruct();
	}


	public RecordingAliasTypeBean(Class<? extends RecordingAliasType<?>> class1) {
		super(class1);
		postConstruct();
	}

	private void postConstruct() {
		baseTypeDescription = new  BaseTypeDescriptionBean<>();
	}


	public BaseBaseTypeDescriptionBean<?> getBaseTypeDescription() {
		return baseTypeDescription;
	}


	public void setBaseTypeDescription(BaseBaseTypeDescriptionBean<?> baseTypeDescription) {
		this.baseTypeDescription = baseTypeDescription;
	}

}
