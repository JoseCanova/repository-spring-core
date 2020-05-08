package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.MediumPosition;
import org.nanotek.entities.BaseMediumPositionBean;
import org.nanotek.proxy.ProxyBase;

public class MediumPositionBean 
<K extends BaseBean<MediumPositionBean<K>,MediumPosition>> 
extends ProxyBase<MediumPositionBean<K>,MediumPosition>
implements BaseMediumPositionBean<MediumPositionBean<K>>{

	private static final long serialVersionUID = -4330192203367829986L;

	public MediumPositionBean() {
		super(castClass());
		postConstructMediumPosition();
	}
	
	private static Class<? extends MediumPosition> castClass() {
		return (Class<? extends MediumPosition>) 
				MediumPosition.class.asSubclass(MediumPosition.class);
	}
	
	public MediumPositionBean(Class<MediumPosition>id) {
		super(id);
		postConstructMediumPosition();
	}

	private void postConstructMediumPosition() {
	}

}
