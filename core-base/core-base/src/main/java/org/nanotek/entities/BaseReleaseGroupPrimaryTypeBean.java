package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseGroupPrimaryTypeBean;
import org.nanotek.beans.entity.ReleaseGroupPrimaryType;

public interface BaseReleaseGroupPrimaryTypeBean
<K extends BaseBean<K,ReleaseGroupPrimaryType<?>>> 
extends Base<K>,
BaseBean<K,ReleaseGroupPrimaryType<?>>{


	public static void main(String[] args) { 
		ReleaseGroupPrimaryTypeBean<?> bean = new ReleaseGroupPrimaryTypeBean<>();
	}
}