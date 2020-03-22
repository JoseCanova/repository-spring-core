package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="string_number_base",
							indexes= {
									@Index(unique = false , name = "string_number_number_idx" , columnList ="number")
								})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public class StringNumberBase<K extends StringNumberBase<K>> extends NumberBase<K,String> {

	private static final long serialVersionUID = -773476453892134913L;

	@Column(name="number" , columnDefinition = "VARCHAR NOT NULL")
	protected String number;
	
	
	public StringNumberBase(String number) {
		super();
		this.number = number;
	}

	public StringNumberBase() {
	}

	@Override
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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
		StringNumberBase other = (StringNumberBase) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StringNumberBase [number=" + number + ", id=" + id + "]";
	}

}
