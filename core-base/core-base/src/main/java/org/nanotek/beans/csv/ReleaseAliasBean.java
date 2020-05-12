package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.entities.BaseReleaseAliasBean;
import org.nanotek.entities.BaseReleaseAliasBeginDateBean;
import org.nanotek.entities.BaseReleaseAliasEndDateBean;
import org.nanotek.entities.BaseReleaseAliasLocaleBean;
import org.nanotek.entities.BaseReleaseAliasSortNameBean;
import org.nanotek.entities.BaseReleaseAliasTypeBean;
import org.nanotek.entities.BaseReleaseBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseAliasBean 
<K extends BaseBean<ReleaseAliasBean<K>,ReleaseAlias<?>>> 
extends ProxyBase<ReleaseAliasBean<K>,ReleaseAlias<?>>
implements BaseReleaseAliasBean<ReleaseAliasBean<K>>{

	private static final long serialVersionUID = 3986721500454057322L;

	BaseReleaseBean<?> release;
	
	BaseReleaseAliasLocaleBean<?> releaseAliasLocale;
	
	BaseReleaseAliasBeginDateBean<?> releaseAliasBeginDate;
	
	BaseReleaseAliasEndDateBean<?> releaseAliasEndDate;
	
	BaseReleaseAliasSortNameBean<?> releaseAliasSortName;
	
	BaseReleaseAliasTypeBean<?> releaseAliasType;

	@SuppressWarnings("unchecked")
	private static Class<? extends ReleaseAlias<?>> castClass() {
		return (Class<? extends ReleaseAlias<?>>) 
				ReleaseAlias.class.asSubclass(ReleaseAlias.class);
	}
	
	
	public ReleaseAliasBean() {
		super(castClass() );
		postContructReleaseALias();
	}


	public ReleaseAliasBean(Class<? extends ReleaseAlias<?>> class1) {
		super(class1);
		postContructReleaseALias();
	}


	private void postContructReleaseALias() {
		release = new ReleaseBean<> ();
		releaseAliasLocale = new ReleaseAliasLocaleBean<> ();
		releaseAliasBeginDate = new ReleaseAliasBeginDateBean<>();
		releaseAliasEndDate = new ReleaseAliasEndDateBean<>();
		releaseAliasType = new  ReleaseAliasTypeBean<> ();
		releaseAliasSortName = new ReleaseAliasSortNameBean<> ();
	}


	public BaseReleaseBean<?> getRelease() {
		return release;
	}


	public void setRelease(BaseReleaseBean<?> release) {
		this.release = release;
	}


	public BaseReleaseAliasLocaleBean<?> getReleaseAliasLocale() {
		return releaseAliasLocale;
	}


	public void setReleaseAliasLocale(BaseReleaseAliasLocaleBean<?> releaseAliasLocale) {
		this.releaseAliasLocale = releaseAliasLocale;
	}


	public BaseReleaseAliasBeginDateBean<?> getReleaseAliasBeginDate() {
		return releaseAliasBeginDate;
	}


	public void setReleaseAliasBeginDate(BaseReleaseAliasBeginDateBean<?> releaseAliasBeginDate) {
		this.releaseAliasBeginDate = releaseAliasBeginDate;
	}


	public BaseReleaseAliasEndDateBean<?> getReleaseAliasEndDate() {
		return releaseAliasEndDate;
	}


	public void setReleaseAliasEndDate(BaseReleaseAliasEndDateBean<?> releaseAliasEndDate) {
		this.releaseAliasEndDate = releaseAliasEndDate;
	}


	public BaseReleaseAliasSortNameBean<?> getReleaseAliasSortName() {
		return releaseAliasSortName;
	}


	public void setReleaseAliasSortName(BaseReleaseAliasSortNameBean<?> releaseAliasSortName) {
		this.releaseAliasSortName = releaseAliasSortName;
	}


	public BaseReleaseAliasTypeBean<?> getReleaseAliasType() {
		return releaseAliasType;
	}


	public void setReleaseAliasType(BaseReleaseAliasTypeBean<?> releaseAliasType) {
		this.releaseAliasType = releaseAliasType;
	}

	

}
