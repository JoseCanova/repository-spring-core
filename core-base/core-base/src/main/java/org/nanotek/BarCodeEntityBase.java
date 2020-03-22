package org.nanotek;

import java.io.Serializable;

public interface BarCodeEntityBase<K extends BarCodeEntityBase<K,B,ID> , B ,  ID extends Serializable > extends IdBase<K,ID>{

	ID getId();
	
	B getBarCode();
    
	void setBarCode(B B);
    
}
