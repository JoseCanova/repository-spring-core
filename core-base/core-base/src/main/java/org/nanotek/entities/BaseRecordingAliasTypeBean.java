package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.RecordingAliasTypeBean;
import org.nanotek.beans.entity.RecordingAliasType;

public interface BaseRecordingAliasTypeBean
<K extends BaseBean<K,RecordingAliasType<?>>> 
extends Base<K>,
BaseBean<K,RecordingAliasType<?>>{

	
	public static void main(String[] args) { 
		RecordingAliasTypeBean<?> bean = new RecordingAliasTypeBean<>();
	}
}
