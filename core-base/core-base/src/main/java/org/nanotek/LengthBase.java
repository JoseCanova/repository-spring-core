package org.nanotek;

import java.io.Serializable;

import org.nanotek.entities.MutableLengthEntity;

public interface LengthBase<T extends Serializable> extends MutableLengthEntity<T>{
	
	T getLength();
	
	
}
