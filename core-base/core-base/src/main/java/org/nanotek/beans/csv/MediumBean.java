package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Medium;
import org.nanotek.entities.BaseMediumBean;
import org.nanotek.entities.BaseMediumCountBean;
import org.nanotek.entities.BaseMediumFormatBean;
import org.nanotek.entities.BaseMediumPositionBean;
import org.nanotek.entities.BaseReleaseBean;
import org.nanotek.proxy.ProxyBase;

public class MediumBean
<K extends BaseBean<MediumBean<K>,Medium<?>>> 
extends ProxyBase<MediumBean<K>,Medium<?>>
implements BaseMediumBean<MediumBean<K>> {


	private static final long serialVersionUID = -8141072962299778762L;


	BaseMediumCountBean<?> mediumCount;
	
	BaseMediumPositionBean<?> mediumPosition;
	
	BaseReleaseBean<?> release;
	
	BaseMediumFormatBean<?> mediumFormat;
	

	public MediumBean() {
		super(castClass());
		postConstructMedium();
	}


	private void postConstructMedium() {
		
		mediumCount = new MediumCountBean<>();
		
		mediumPosition = new MediumPositionBean<> ();
		
		release = new ReleaseBean<>();
		
		mediumFormat = new MediumFormatBean<> ();
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends Medium<?>> castClass() {
		return (Class<? extends Medium<?>>) Medium.class.asSubclass(Medium.class);
	}


	public MediumBean(Class<? extends Medium<?>> class1) {
		super(class1);
		postConstructMedium() ;
	}


	public BaseMediumCountBean<?> getMediumCount() {
		return mediumCount;
	}


	public void setMediumCount(BaseMediumCountBean<?> mediumCount) {
		this.mediumCount = mediumCount;
	}


	public BaseMediumPositionBean<?> getMediumPosition() {
		return mediumPosition;
	}


	public void setMediumPosition(BaseMediumPositionBean<?> mediumPosition) {
		this.mediumPosition = mediumPosition;
	}


	public BaseReleaseBean<?> getRelease() {
		return release;
	}


	public void setRelease(BaseReleaseBean<?> release) {
		this.release = release;
	}


	public BaseMediumFormatBean<?> getMediumFormat() {
		return mediumFormat;
	}


	public void setMediumFormat(BaseMediumFormatBean<?> mediumFormat) {
		this.mediumFormat = mediumFormat;
	}

}
