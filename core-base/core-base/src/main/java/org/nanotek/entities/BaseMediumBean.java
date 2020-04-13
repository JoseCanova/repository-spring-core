package org.nanotek.entities;

import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.beans.csv.MediumBean;
import org.nanotek.beans.entity.Medium;

public interface BaseMediumBean
<K extends BaseBean<K,Medium<?>>> 
extends Base<K>,
BaseBean<K,Medium<?>>{

	
	public static void main(String[] args) { 
		MediumBean<?> bean = new MediumBean<>();
	}

}
