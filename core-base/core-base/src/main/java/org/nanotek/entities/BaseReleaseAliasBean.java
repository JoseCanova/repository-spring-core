package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseAliasBean;
import org.nanotek.beans.entity.ReleaseAlias;

public interface BaseReleaseAliasBean
<K extends BaseBean<K,ReleaseAlias<?>>> 
extends Base<K>,
BaseBean<K,ReleaseAlias<?>>{

	
	public static void main(String[] args) { 
		ReleaseAliasBean<?> bean = new ReleaseAliasBean<>();
	}
}
