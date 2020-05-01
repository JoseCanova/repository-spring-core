package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Label;
import org.nanotek.entities.BaseAreaBean;
import org.nanotek.entities.BaseLabelBean;
import org.nanotek.entities.BaseLabelBeginDateBean;
import org.nanotek.entities.BaseLabelEndDateBean;
import org.nanotek.entities.BaseLabelTypeBean;
import org.nanotek.proxy.ProxyBase;

public class LabelBean 
<K extends BaseBean<LabelBean<K>,Label<?>>> 
extends ProxyBase<LabelBean<K>,Label<?>>
implements BaseLabelBean<LabelBean<K>>{
	
	private static final long serialVersionUID = -7554336217060155004L;

	private BaseAreaBean<?> area;
	
	private BaseLabelBeginDateBean<?> labelBeginDate;
	
	private BaseLabelEndDateBean<?> labelEndDate;
	
	private BaseLabelTypeBean<?> labelType;
	
	public LabelBean() {
		super(castClass());
		prepareLabelBean();
	}
	
	public LabelBean(Class<Label<?>>id) {
		super(id);
		prepareLabelBean();
	}

	private void prepareLabelBean() { 
		
		area = new AreaBean<>();
		
		labelBeginDate = new LabelBeginDateBean<> ();
		
		labelEndDate = new LabelEndDateBean<> ();
		
		labelType = new LabelTypeBean<> ();
		
	}
	
	@SuppressWarnings("unchecked")
	private static Class<? extends Label<?>> castClass() {
		return (Class<? extends Label<?>>) 
				Label.class.asSubclass(Label.class);
	}

	public BaseAreaBean<?> getArea() {
		return area;
	}

	public void setArea(BaseAreaBean<?> area) {
		this.area = area;
	}

	public BaseLabelBeginDateBean<?> getLabelBeginDate() {
		return labelBeginDate;
	}

	public void setLabelBeginDate(BaseLabelBeginDateBean<?> labelBeginDate) {
		this.labelBeginDate = labelBeginDate;
	}

	public BaseLabelEndDateBean<?> getLabelEndDate() {
		return labelEndDate;
	}

	public void setLabelEndDate(BaseLabelEndDateBean<?> labelEndDate) {
		this.labelEndDate = labelEndDate;
	}

	public BaseLabelTypeBean<?> getLabelType() {
		return labelType;
	}

	public void setLabelType(BaseLabelTypeBean<?> labelType) {
		this.labelType = labelType;
	}
	
	
}
