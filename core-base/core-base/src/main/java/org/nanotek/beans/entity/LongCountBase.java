package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseLongCountBaseEntity;

@Entity
@Table(name="long_count_base",
				indexes= {
						@Index(unique = false , name = "long_count_field_idx" , columnList ="count")
					})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
public class LongCountBase<K extends LongCountBase<K>> extends CountBase<K> implements BaseLongCountBaseEntity<K>{

	private static final long serialVersionUID = 7485578978795549390L;
	
	@NotNull
	@Column(name="count",nullable=false)
	protected Long count;

	public LongCountBase(@NotNull Long count) {
		super();
		this.count = count;
	}

	public LongCountBase() {
	}

	@Override
	public Long count() {
		return count;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((count == null) ? 0 : count.hashCode());
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
		LongCountBase other = (LongCountBase) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LongCountBase [count=" + count + ", id=" + id + "]";
	}

}
