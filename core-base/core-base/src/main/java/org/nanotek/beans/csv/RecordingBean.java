package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Recording;
import org.nanotek.beans.entity.Track;
import org.nanotek.entities.BaseRecordingBean;
import org.nanotek.proxy.ProxyBase;

public class RecordingBean  
<K extends BaseBean<RecordingBean<K>,Recording<?>>> 
extends ProxyBase<RecordingBean<K>,Recording<?>>
implements BaseRecordingBean<RecordingBean<K>>{

	private static final long serialVersionUID = 2926594064752891481L;

	public RecordingBean(Class<? extends Recording<?>> class1) {
		super(class1);
		// TODO Auto-generated constructor stub
	}

	public RecordingBean() {
		super(castClass());
	}


	private static Class<? extends Recording<?>> castClass() {
		return (Class<? extends Recording<?>>) Recording.class.asSubclass(Recording.class);
	}


	//	
	//	@NotNull
	//	private Long recordingId;
	//	@NotNull
	//	private String gid;
	//	@NotNull
	//	private String name;
	//	@NotNull
	//	private Long artistCredit;
	//    private Long length;
	//    private String comment;
	//    private Long editsPending;
	//    private String lastUpdated;
	//    private String  video;



}
