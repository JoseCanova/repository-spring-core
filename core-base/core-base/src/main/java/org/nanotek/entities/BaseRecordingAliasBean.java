package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.RecordingAliasBean;
import org.nanotek.beans.entity.RecordingAlias;

public interface BaseRecordingAliasBean
<K extends BaseBean<K,RecordingAlias<?>>> 
extends Base<K>,
BaseBean<K,RecordingAlias<?>>{

	
	public static void main(String[] args) { 
		RecordingAliasBean<?> bean = new RecordingAliasBean<>();
	}
}
