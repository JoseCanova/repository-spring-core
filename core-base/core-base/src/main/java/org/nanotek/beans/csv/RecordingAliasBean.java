package org.nanotek.beans.csv;

import org.nanotek.BaseBean;
import org.nanotek.beans.entity.Gender;
import org.nanotek.beans.entity.RecordingAlias;
import org.nanotek.entities.BaseRecordingAliasBean;
import org.nanotek.proxy.ProxyBase;

public class RecordingAliasBean 
<K extends BaseBean<RecordingAliasBean<K>,RecordingAlias<?>>> 
extends ProxyBase<RecordingAliasBean<K>,RecordingAlias<?>>
implements BaseRecordingAliasBean<RecordingAliasBean<K>>{


	private static final long serialVersionUID = 4772995387500387928L;

	
//	public Long resultId;
//	public Long recording; 
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
//	
//	
	@SuppressWarnings("unchecked")
	private static Class<? extends RecordingAlias<?>> castClass() {
		return (Class<? extends RecordingAlias<?>>) 
				RecordingAlias.class.asSubclass(RecordingAlias.class);
	}


	public RecordingAliasBean() {
		super(castClass());
	}


	public RecordingAliasBean(Class<? extends RecordingAlias<?>> class1) {
		super(class1);
		// TODO Auto-generated constructor stub
	}


}
