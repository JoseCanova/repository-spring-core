package org.nanotek.beans.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "InstrumentType")
public class InstrumentType<K extends InstrumentType<K>> extends BaseType<K> {

	
	private static final long serialVersionUID = 1526958848784766177L;

	@OneToMany(fetch = FetchType.LAZY , mappedBy = "instrumentType")
	private Set<Instrument<?>> instruments;
	
	public InstrumentType() {
	}

	public InstrumentType(@NotNull Long typeId) {
		super(typeId);
	}

	public InstrumentType(@NotNull UUID gid, @NotBlank String name) {
		super(gid, name);
	}

	public InstrumentType(@NotNull Long typeId, @NotNull UUID gid, @NotBlank String name) {
		super(typeId, gid, name);
	}

	public Set<Instrument<?>> getInstruments() {
		return instruments;
	}

	public void setInstruments(Set<Instrument<?>> instruments) {
		this.instruments = instruments;
	}

}
