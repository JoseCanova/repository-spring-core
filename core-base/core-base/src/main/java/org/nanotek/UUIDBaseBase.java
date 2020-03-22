package org.nanotek;

import java.util.UUID;

public interface UUIDBaseBase extends ImmutableBase<UUIDBaseBase,UUIDBaseBase> {

	String getName();
	
	void setName(String name);
	
	default UUID getUUID() {
		return withUUID();
	}
	
	default UUIDBaseBase  withName(String name) { 
		setName(name);
		withUUID();
		return this;
	}
	
}
