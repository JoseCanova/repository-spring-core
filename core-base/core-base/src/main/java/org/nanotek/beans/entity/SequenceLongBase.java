package org.nanotek.beans.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseSequenceLongBaseEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Table(name="sequence_long_base")
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class SequenceLongBase<K extends SequenceLongBase<K,ID>, ID extends Serializable> implements  BaseSequenceLongBaseEntity<K, ID>{

	private static final long serialVersionUID = 1932266128563675834L;
	
	@Id
	@NotNull
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sequence_id_seq")
	@SequenceGenerator(name = "sequence_id_seq", sequenceName = "sequence_id_seq")
	protected ID id;

	public SequenceLongBase() {}
	
	public SequenceLongBase(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return this.id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
}
