package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ArtistType;
import org.nanotek.beans.entity.Release;
import org.nanotek.entities.BaseReleaseBean;
import org.nanotek.proxy.ProxyBase;

/**
 * 
 * @author josecanova
 *
 */
public class ReleaseBean 
<K extends BaseBean<ReleaseBean<K>,Release<?>>> 
extends ProxyBase<ReleaseBean<K>,Release<?>>
implements BaseReleaseBean<ReleaseBean<K>>{


	
	
	
	private static final long serialVersionUID = -11618084576388817L;
	
	
//	public Long releaseId; 
//	public String gid; 
//	public String name;
//	public Long artistCreditId; 
//	public Long releaseGroup; 
//	public Long status; 
//	public Long  packaging; 
//	public Long  language; 
//	public Integer script;
//	public String barcode; 
//	public String comment; 
//	public Integer editsPending; 
//	public Integer quality; 
//	public String lastUpdated;
//	
	public ReleaseBean(Class<? extends Release<?>> class1) {
		super(class1);
	}


	@SuppressWarnings("unchecked")
	private static Class<? extends Release<?>> castClass() {
		return (Class<? extends Release<?>>) Release.class.asSubclass(Release.class);
	}



	public ReleaseBean() {
		super(castClass());
	}
	
	

}
