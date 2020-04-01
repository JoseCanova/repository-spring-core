package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.RecordingBean;
import org.nanotek.beans.entity.Recording;

public interface BaseRecordingBean
<K extends BaseBean<K,Recording<?>>> 
extends Base<K>,
BaseBean<K,Recording<?>>{

	
	public static void main(String[] args) { 
		RecordingBean<?> bean = new RecordingBean<>();
	}
}
