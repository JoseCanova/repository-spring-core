package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.WorkComment;
import org.nanotek.entities.BaseWorkCommentBean;
import org.nanotek.proxy.ProxyBase;

public class WorkCommentBean 
<K extends BaseBean<WorkCommentBean<K>,WorkComment<?>>> 
extends ProxyBase<WorkCommentBean<K>,WorkComment<?>>
implements BaseWorkCommentBean<WorkCommentBean<K>>{

	private static final long serialVersionUID = -8805757770026493995L;

	public WorkCommentBean(Class<WorkComment<?>> id) {
		super(id);
	}


	public WorkCommentBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends WorkComment<?>> castClass() {
		return (Class<? extends WorkComment<?>>) WorkComment.class.asSubclass(WorkComment.class);
	}

	public static void main(String[] args) {
		WorkCommentBean bean = new WorkCommentBean();
		bean.setComment("this is a comment");
		System.out.println(bean.getComment());
	}

}
