package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.ReleaseBean;
import org.nanotek.beans.entity.Release;

public interface BaseReleaseBean<K extends BaseBean<K,Release<?>>> 
extends Base<K>,
BaseBean<K,Release<?>>{


	public static void main(String[] args) { 
		ReleaseBean<?> bean = new ReleaseBean<>();
	}
}
