package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseGroupComment;
import org.nanotek.entities.BaseReleaseGroupCommentBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseGroupCommentBean 
<K extends BaseBean<ReleaseGroupCommentBean<K>,ReleaseGroupComment<?>>> 
extends ProxyBase<ReleaseGroupCommentBean<K>,ReleaseGroupComment<?>>
implements BaseReleaseGroupCommentBean<ReleaseGroupCommentBean<K>>{

	private static final long serialVersionUID = -8805757770026493995L;

	public ReleaseGroupCommentBean(Class<ReleaseGroupComment<?>> id) {
		super(id);
	}


	public ReleaseGroupCommentBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseGroupComment<?>> castClass() {
		return (Class<? extends ReleaseGroupComment<?>>) ReleaseGroupComment.class.asSubclass(ReleaseGroupComment.class);
	}

	public static void main(String[] args) {
		ReleaseGroupCommentBean bean = new ReleaseGroupCommentBean();
		bean.setComment("this is a comment");
		System.out.println(bean.getComment());
	}

}
