package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.TrackBean;
import org.nanotek.beans.entity.Track;

public interface BaseTrackBeanBean
<K extends BaseBean<K,Track<?>>> 
extends Base<K>,
BaseBean<K,Track<?>>{


	public static void main(String[] args) { 
		TrackBean<?> bean = new TrackBean<>();
	}
}