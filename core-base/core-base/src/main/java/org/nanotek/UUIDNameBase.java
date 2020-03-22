package org.nanotek;

public class UUIDNameBase implements UUIDBaseBase {

	private static final long serialVersionUID = 2692875536991084184L;

	private String name; 
	
	
	public UUIDNameBase() {
		withUUID();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "UUIDNameBase [name=" + name + ", uuid=" + getUUID().toString() + "]";
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public UUIDBaseBase getId() {
		return this;
	}
	
}
