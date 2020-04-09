package org.nanotek;

import java.util.Optional;

import org.nanotek.opencsv.CsvResult;

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
	
	public int compareTo(UUIDBaseBase to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = CsvResult.class.cast(obj);
				return this.compareTo((UUIDBaseBase) theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}
	
	
}
