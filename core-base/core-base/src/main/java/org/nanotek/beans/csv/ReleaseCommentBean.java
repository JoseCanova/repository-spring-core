package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseComment;
import org.nanotek.entities.BaseReleaseCommentBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseCommentBean 
<K extends BaseBean<ReleaseCommentBean<K>,ReleaseComment<?>>> 
extends ProxyBase<ReleaseCommentBean<K>, ReleaseComment<?>>
implements BaseReleaseCommentBean<ReleaseCommentBean<K>>{

	private static final long serialVersionUID = 1925289307230205173L;


	public ReleaseCommentBean() {
		super(castClass());
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseComment<?>> castClass() {
		return (Class<? extends ReleaseComment<?>>) 
				ReleaseComment.class.
				asSubclass(ReleaseComment.class);
	}
	
	
	public ReleaseCommentBean(Class<? extends ReleaseComment<?>> class1) {
		super(class1);
	}
	
}
