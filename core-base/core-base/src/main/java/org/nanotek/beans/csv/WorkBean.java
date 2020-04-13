package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Work;
import org.nanotek.beans.entity.WorkComment;
import org.nanotek.beans.entity.WorkType;
import org.nanotek.entities.BaseWorkBean;
import org.nanotek.proxy.ProxyBase;

public class WorkBean 
<K extends BaseBean<WorkBean<K>,Work<?>>> 
extends ProxyBase<WorkBean<K>,Work<?>>
implements BaseWorkBean<WorkBean<K>>
{
	
	public WorkComment<?> workComment;
	
	public WorkType<?> workType;
	
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
		
		workComment = new WorkComment<>();
		
		workType = new WorkType<>();
	}

	public WorkComment<?> getWorkComment() {
		return workComment;
	}

	public void setWorkComment(WorkComment<?> workComment) {
		this.workComment = workComment;
	}

	public WorkType<?> getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType<?> workType) {
		this.workType = workType;
	}

}
