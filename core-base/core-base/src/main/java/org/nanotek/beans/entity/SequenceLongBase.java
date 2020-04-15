package org.nanotek.beans.entity;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.Base;
import org.nanotek.entities.BaseSequenceLongBaseEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@MappedSuperclass
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class SequenceLongBase<K extends SequenceLongBase<K,ID>, ID extends Serializable> implements  BaseSequenceLongBaseEntity<K, ID>{

	private static final long serialVersionUID = 1932266128563675834L;
	
	@Id
	@NotNull(groups = {Default.class})
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sequence_id_seq")
	@SequenceGenerator(name = "sequence_id_seq", sequenceName = "sequence_id_seq",allocationSize = 5, initialValue= 1)
	protected ID id;

	public SequenceLongBase() {
	}
	
	public SequenceLongBase(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return this.id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(K to) {
		return withUUID().compareTo(to.withUUID());
	}
	
	@Override
	public boolean equals(Object obj) {
			boolean b = Optional.ofNullable(obj).isPresent();
			if (b) {
				Base theBase = this.getClass().cast(obj);
				return this.compareTo((K) theBase) == 0;}
			return false;
	}
	
	@Override
	public int hashCode() {
		return md5Digest().hashCode();
	}
}
