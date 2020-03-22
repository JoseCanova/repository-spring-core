package org.nanotek.entities;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.nanotek.Nameable;

public interface MutableName<K extends Serializable> extends Nameable<K>{
	
	void setName(@NotBlank K name);
	
	
}
