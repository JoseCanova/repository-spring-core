package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleasePackagingBean;
import org.nanotek.beans.entity.ReleasePackaging;

public interface BaseReleasePackagingBean
<K extends BaseBean<K,ReleasePackaging<?>>> 
extends Base<K>,
BaseBean<K,ReleasePackaging<?>>{


	public static void main(String[] args) { 
		ReleasePackagingBean<?> bean = new ReleasePackagingBean<>();
	}
}
