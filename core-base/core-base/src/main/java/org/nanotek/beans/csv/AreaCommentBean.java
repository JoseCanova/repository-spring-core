package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.AreaComment;
import org.nanotek.entities.BaseAreaCommentBean;
import org.nanotek.opencsv.CsvBaseBean;

public class AreaCommentBean 
<K extends BaseBean<AreaCommentBean<K>,AreaComment<?>>> 
extends CsvBaseBean<AreaCommentBean<K>,AreaComment<?>>
implements BaseAreaCommentBean<AreaCommentBean<K>>{

	private static final long serialVersionUID = -8805757770026493995L;

	public AreaCommentBean(Class<AreaComment<?>> id) {
		super(id);
	}

	public static void main(String[] args) {
		AreaCommentBean bean = new AreaCommentBean(AreaComment.class);
		bean.setComment("this is a comment");
		System.out.println(bean.getComment());
	}
	
}
