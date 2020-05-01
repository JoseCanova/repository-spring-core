package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Isrc;
import org.nanotek.entities.BaseIsrcBean;
import org.nanotek.entities.BaseRecordingBean;
import org.nanotek.proxy.ProxyBase;

public class IsrcBean 
<K extends BaseBean<IsrcBean<K>,Isrc<?>>> 
extends ProxyBase<IsrcBean<K>,Isrc<?>>
implements BaseIsrcBean<IsrcBean<K>>{

	private static final long serialVersionUID = 1206216723520560368L;
	
	private BaseRecordingBean<?> recording;

	public IsrcBean() {
		super(castClass());
		postConstruct();
	}
	
	public IsrcBean(Class<Isrc<?>>id) {
		super(id);
		postConstruct();
	}

	
	@SuppressWarnings("unchecked")
	private static Class<? extends Isrc<?>> castClass() {
		return (Class<? extends Isrc<?>>) 
				Isrc.class.asSubclass(Isrc.class);
	}
	
	private void postConstruct() { 
		recording = new RecordingBean<>();
	}


	public BaseRecordingBean<?> getRecording() {
		return recording;
	}


	public void setRecording(BaseRecordingBean<?> recording) {
		this.recording = recording;
	}
	
}
