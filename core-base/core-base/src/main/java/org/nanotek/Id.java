package org.nanotek;

public interface Id<ID> {
	
	ID getId();
	
	static  String NULL_VALUE () { 
		return "0";
	}
	
	default Id<?> asId() {
		return this.getClass().asSubclass(Id.class).cast(this);
	}
		
}
