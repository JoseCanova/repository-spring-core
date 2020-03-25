package org.nanotek;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutablePositionEntity;

public class StringPositionBase
<SB extends StringPositionBase<SB> > 
implements Base<SB> , Id<String> , MutablePositionEntity <Integer> {

	
	private static final long serialVersionUID = 6269258926854497193L;

	@NotBlank String id;
	
	@NotNull Integer  position; 
	
	public StringPositionBase() {
	}

	public StringPositionBase(String id, Integer  position) {
		super();
		this.id = id;
		this.position = position;
	}


	@Override
	public Integer getPosition() {
		return position;
	}

	@Override
	public void setPosition(Integer  k) {
		this.position = k;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int compareTo(SB to) {
		Optional.ofNullable(to).orElseThrow(BaseException::new);
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				StringPositionBase theBase = StringPositionBase.class.cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}

}
