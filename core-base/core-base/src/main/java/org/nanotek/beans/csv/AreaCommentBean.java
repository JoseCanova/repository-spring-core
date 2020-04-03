package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.beans.entity.AreaEndDate;
import org.nanotek.entities.BaseAreaCommentBean;
import org.nanotek.proxy.ProxyBase;

public class AreaCommentBean 
<K extends BaseBean<AreaCommentBean<K>,AreaComment<?>>> 
extends ProxyBase<AreaCommentBean<K>,AreaComment<?>>
implements BaseAreaCommentBean<AreaCommentBean<K>>{

	private static final long serialVersionUID = -8805757770026493995L;

	public AreaCommentBean(Class<AreaComment<?>> id) {
		super(id);
	}


	public AreaCommentBean() {
		super(castClass());
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends AreaComment<?>> castClass() {
		return (Class<? extends AreaComment<?>>) AreaComment.class.asSubclass(AreaComment.class);
	}

	public static void main(String[] args) {
		AreaCommentBean bean = new AreaCommentBean();
		bean.setComment("this is a comment");
		System.out.println(bean.getComment());
	}

}
