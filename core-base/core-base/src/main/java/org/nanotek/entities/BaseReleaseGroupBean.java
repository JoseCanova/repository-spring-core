package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseGroupBean;
import org.nanotek.beans.entity.ReleaseGroup;

public interface BaseReleaseGroupBean
<K extends BaseBean<K,ReleaseGroup<?>>> 
extends Base<K>,
BaseBean<K,ReleaseGroup<?>>{


	public static void main(String[] args) { 
		ReleaseGroupBean<?> bean = new ReleaseGroupBean<>();
	}
}