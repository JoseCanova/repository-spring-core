package org.nanotek.beans.csv;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.ImmutableBase;
import org.nanotek.beans.entity.ReleaseAlias;
import org.nanotek.beans.entity.ReleaseAliasType;
import org.nanotek.entities.BaseReleaseAliasBean;
import org.nanotek.entities.BaseReleaseAliasTypeBean;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.proxy.ProxyBase;

public class ReleaseAliasTypeBean 
<K extends BaseBean<ReleaseAliasTypeBean<K>,ReleaseAliasType<?>>> 
extends ProxyBase<ReleaseAliasTypeBean<K>,ReleaseAliasType<?>>
implements BaseReleaseAliasTypeBean<ReleaseAliasTypeBean<K>>{

	private static final long serialVersionUID = -6271568961378072618L;

	
//	
//	private Long resultAliasTypeId; 
//	private String name; 
//	private Long parent; 
//	private Long childOrder; 
//	private String description; 
//	private String gid;
	


	public ReleaseAliasTypeBean() {
		super((Class<? extends ReleaseAliasType<?>>) ReleaseAliasType.class);
	}


	public ReleaseAliasTypeBean(Class<? extends ReleaseAliasType<?>> class1) {
		super(class1);
		// TODO Auto-generated constructor stub
	}




}
