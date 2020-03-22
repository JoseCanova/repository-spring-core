package org.nanotek.beans.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class InstrumentType<K extends InstrumentType<K>> extends BaseType<K> {

	
	private static final long serialVersionUID = 1526958848784766177L;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Instrument<?>> instruments;
	
	public InstrumentType() {
	}

	public InstrumentType(@NotBlank @Length(min = 1, max = 50) String gid, @NotBlank String name) {
		super(gid, name);
	}

	public InstrumentType(@NotBlank String name) {
		super(name);
	}


	public Set<Instrument<?>> getInstruments() {
		return instruments;
	}


	public void setInstruments(Set<Instrument<?>> instruments) {
		this.instruments = instruments;
	}


}
