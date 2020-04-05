package org.nanotek;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.MutableValueEntity;

public class ValueBase
<SB extends ValueBase<SB> > 
implements Base<SB> , Id<Integer> , MutableValueEntity <String> {

	
	private static final long serialVersionUID = 6269258926854497193L;

	@NotNull Integer id;
	
	 @NotBlank  String  value; 
	
	public ValueBase() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ValueBase(Integer id, String  value) {
		super();
		this.id = id;
		this.value = value;
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
				ValueBase theBase = ValueBase.class.cast(obj);
				return this.compareTo(theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}

	public static ValueBase<?> of(Integer id, String value){ 
		return ValueBase.class.asSubclass(ValueBase.class).cast(new ValueBase<>(id,value));
	}
	
}
