package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseAliasTypeBean;
import org.nanotek.beans.entity.ReleaseAliasType;

public interface BaseReleaseAliasTypeBean
<K extends BaseBean<K,ReleaseAliasType<?>>> 
extends Base<K>,
BaseBean<K,ReleaseAliasType<?>>{


	public static void main(String[] args) { 
		ReleaseAliasTypeBean<?> bean = new ReleaseAliasTypeBean<>();
	}
}
