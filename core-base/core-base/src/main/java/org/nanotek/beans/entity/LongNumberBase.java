package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="long_number_base")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public class LongNumberBase<K extends LongNumberBase<K>> extends NumberBase<K,Long> {

	private static final long serialVersionUID = 6726812273313773460L;
	
	@NotNull
	@Column(name="number" , nullable=false)
	protected Long number;
	
	public LongNumberBase(@NotNull Long number) {
		super();
		this.number = number;
	}

	public LongNumberBase() {
	}

	@Override
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LongNumberBase other = (LongNumberBase) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LongBase [number=" + number + ", id=" + id + "]";
	}
	
}
