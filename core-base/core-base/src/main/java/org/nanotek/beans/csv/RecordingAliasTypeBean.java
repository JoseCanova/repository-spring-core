package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.RecordingAlias;
import org.nanotek.beans.entity.RecordingAliasType;
import org.nanotek.entities.BaseRecordingAliasTypeBean;
import org.nanotek.proxy.ProxyBase;

public class RecordingAliasTypeBean 
<K extends BaseBean<RecordingAliasTypeBean<K>,RecordingAliasType<?>>> 
extends ProxyBase<RecordingAliasTypeBean<K>,RecordingAliasType<?>>
implements BaseRecordingAliasTypeBean<RecordingAliasTypeBean<K>>{

	private static final long serialVersionUID = 2632943505939712312L;
	
	
//	public Long recordingAliasTypeId; 
//	public String name; 
//	public Long parent; 
//	public Long childOrder; 
//	public String description; 
//	public String gid;
//	

	@SuppressWarnings("unchecked")
	private static Class<? extends RecordingAliasType<?>> castClass() {
		return (Class<? extends RecordingAliasType<?>>) 
				RecordingAliasType.class.asSubclass(RecordingAliasType.class);
	}
	
	
	public RecordingAliasTypeBean() {
		super(castClass());
	}


	public RecordingAliasTypeBean(Class<? extends RecordingAliasType<?>> class1) {
		super(class1);
	}


}
