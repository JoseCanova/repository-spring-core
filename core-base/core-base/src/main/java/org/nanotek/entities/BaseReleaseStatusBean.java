package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseStatusBean;
import org.nanotek.beans.entity.ReleaseStatus;

public interface BaseReleaseStatusBean
<K extends BaseBean<K,ReleaseStatus<?>>> 
extends Base<K>,
BaseBean<K,ReleaseStatus<?>>{


	public static void main(String[] args) { 
		ReleaseStatusBean<?> bean = new ReleaseStatusBean<>();
	}
}
