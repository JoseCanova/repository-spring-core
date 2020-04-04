package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.RecordingLength;
import org.nanotek.entities.BaseRecordingLengthBean;
import org.nanotek.proxy.ProxyBase;

public class RecordingLengthBean 
<K extends BaseBean<RecordingLengthBean<K>,RecordingLength<?>>> 
extends ProxyBase<RecordingLengthBean<K>,RecordingLength<?>>
implements BaseRecordingLengthBean<RecordingLengthBean<K>>{

	
	private static final long serialVersionUID = -4687033606555338527L;

	public RecordingLengthBean() {
		super(castClass());
	}
	
	private static Class<? extends RecordingLength<?>> castClass() {
		return (Class<? extends RecordingLength<?>>) RecordingLength.class.asSubclass(RecordingLength.class);
	}

	public RecordingLengthBean(Class<? extends RecordingLength<?>> class1) {
		super(class1);
	}
	
	
}
