package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.RecordingComment;
import org.nanotek.entities.BaseRecordingCommentBean;
import org.nanotek.proxy.ProxyBase;

public class RecordingCommentBean 
<K extends BaseBean<RecordingCommentBean<K>,RecordingComment<?>>> 
extends ProxyBase<RecordingCommentBean<K>,RecordingComment<?>>
implements BaseRecordingCommentBean<RecordingCommentBean<K>>
{
	
	private static final long serialVersionUID = -6876436921665460974L;

	public RecordingCommentBean(Class<RecordingComment<?>> id) {
		super(id);
	}


	public RecordingCommentBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends RecordingComment<?>> castClass() {
		return (Class<? extends RecordingComment<?>>) RecordingComment.class.asSubclass(RecordingComment.class);
	}


}
