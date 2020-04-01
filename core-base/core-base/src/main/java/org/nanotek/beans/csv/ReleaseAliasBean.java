package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.entities.BaseReleaseAliasBean;
import org.nanotek.proxy.ProxyBase;

public class ReleaseAliasBean 
<K extends BaseBean<ReleaseAliasBean<K>,ReleaseAlias<?>>> 
extends ProxyBase<ReleaseAliasBean<K>,ReleaseAlias<?>>
implements BaseReleaseAliasBean<ReleaseAliasBean<K>>{

	private static final long serialVersionUID = 3986721500454057322L;

	
//	public Long releaseAliasId; 
//	public Long release; 
//	public String name; 
//	public String locale; 
//	public Integer editsPending; 
//	public String lastUpdated; 
//	public Long type; 
//	public String sortName;
//	public Integer beginDateYear ;
//	public Integer beginDateMonth;
//	public Integer beginDateDay;
//	public Integer endDateYear;
//	public Integer endDateMonth;
//	public Integer endDateDay; 
//	public String primaryForLocale;
//	public String ended;



	
	
	public ReleaseAliasBean() {
		super((Class<? extends ReleaseAlias<?>>) ReleaseAlias.class);
	}


	public ReleaseAliasBean(Class<? extends ReleaseAlias<?>> class1) {
		super(class1);
	}


}
