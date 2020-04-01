package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.LanguageBean;
import org.nanotek.beans.entity.Language;

public interface BaseLanguageBean
<K extends BaseBean<K,Language<?>>> 
extends Base<K>,
BaseBean<K,Language<?>>{

	
	public static void main(String[] args) { 
		LanguageBean<?> language = new LanguageBean<>();
	}
}
