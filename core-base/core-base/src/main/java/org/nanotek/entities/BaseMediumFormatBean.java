package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.MediumFormatBean;
import org.nanotek.beans.entity.MediumFormat;

public interface BaseMediumFormatBean
<K extends BaseBean<K,MediumFormat<?>>> 
extends Base<K>,
BaseBean<K,MediumFormat<?>>{

	
	public static void main(String[] args) { 
		MediumFormatBean<?> language = new MediumFormatBean<>();
	}
}
