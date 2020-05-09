package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Work;
import org.nanotek.entities.BaseWorkBean;
import org.nanotek.entities.BaseWorkCommentBean;
import org.nanotek.entities.BaseWorkTypeBean;
import org.nanotek.proxy.ProxyBase;

public class WorkBean 
<K extends BaseBean<WorkBean<K>,Work<?>>> 
extends ProxyBase<WorkBean<K>,Work<?>>
implements BaseWorkBean<WorkBean<K>>
{
	
	private static final long serialVersionUID = -7506936733635556154L;

	public BaseWorkCommentBean<?> workComment;
	
	public BaseWorkTypeBean<?> workType;
	
	public WorkBean() {
		super(castClass());
		postConstruct();
	}

	public WorkBean(Class<Work<?>> class1) {
		super(class1);
		postConstruct();
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends Work<?>> castClass() {
		return (Class<? extends Work<?>>) Work.class.asSubclass(Work.class);
	}

	private void postConstruct() {
		
		workComment = new WorkCommentBean<>();
		
		workType = new WorkTypeBean<>();
	}

	public BaseWorkCommentBean<?> getWorkComment() {
		return workComment;
	}

	public void setWorkComment(BaseWorkCommentBean<?> workComment) {
		this.workComment = workComment;
	}

	public BaseWorkTypeBean<?> getWorkType() {
		return workType;
	}

	public void setWorkType(BaseWorkTypeBean<?> workType) {
		this.workType = workType;
	}

}
