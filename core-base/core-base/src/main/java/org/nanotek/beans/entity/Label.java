package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseLabelEntity;
import org.nanotek.entities.MutableAreaEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableLabelBeginDateEntity;
import org.nanotek.entities.MutableLabelCodeEntity;
import org.nanotek.entities.MutableLabelEndDateEntity;
import org.nanotek.entities.MutableLabelNameEntity;
import org.nanotek.entities.MutableLabelTypeEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@SuppressWarnings("serial")
@Entity
@Table(name="label",
uniqueConstraints = {@UniqueConstraint(name="uk_label_id" , columnNames = {"label_id"})})
public class Label<K extends Label<K>> 
extends BrainzBaseEntity<K>
implements BaseLabelEntity<K>,
MutableGidEntity<UUID>,MutableLabelNameEntity<String>,
MutableLabelBeginDateEntity<LabelBeginDate<?>>,
MutableLabelEndDateEntity<LabelEndDate<?>>,
MutableLabelTypeEntity<LabelType<?>>,
MutableAreaEntity<Area<?>>,
MutableLabelCodeEntity<Integer>{
	
	@NotNull(groups =  {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="label_id")
	private Long labelId;

	@NotNull(groups =  {PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	protected UUID gid;
	
	@NotNull(groups =  {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String labelName;

	@NotNull(groups =  {PrePersistValidationGroup.class})
	@ManyToOne(optional = false , cascade = CascadeType.MERGE)
	@JoinTable(name = "label_type_join",joinColumns = 
	@JoinColumn(name = "label_id" , referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "label_type_id" , referencedColumnName = "id"))
	public LabelType<?> labelType;
	
	@Column
	private Integer labelCode; 
	
	@OneToOne
	@JoinColumn(name = "label_begin_date_id",referencedColumnName = "id")
	public LabelBeginDate<?> labelBeginDate;
	
	@OneToOne
	@JoinColumn(name = "label_end_date_id" , referencedColumnName = "id")
	public LabelEndDate<?> labelEndDate;
	
	@ManyToOne
	public Area<?> area;
	
	public Label(@NotBlank String sortName, Long labelId, @NotNull UUID gid, @NotNull String name,
			LabelType<?> labelType) {
		this.labelId = labelId;
		this.gid = gid;
		this.labelName = name;
		this.labelType = labelType;
	}
	


	public Label() {
	}


	@BrainzKey(entityClass = Label.class, pathName = "labelId")
	public Long getLabelId() {
		return labelId;
	}



	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String name) {
		this.labelName = name;
	}

	public LabelType<?> getLabelType() {
		return labelType;
	}

	public void setLabelType(LabelType<?> labelType) {
		this.labelType = labelType;
	}
	
	public Integer getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(Integer labelCode) {
		this.labelCode = labelCode;
	}

	public LabelBeginDate<?> getLabelBeginDate() {
		return labelBeginDate;
	}

	public void setLabelBeginDate(LabelBeginDate<?> labelBeginDate) {
		this.labelBeginDate = labelBeginDate;
	}

	public LabelEndDate<?> getLabelEndDate() {
		return labelEndDate;
	}

	public void setLabelEndDate(LabelEndDate<?> labelEndDate) {
		this.labelEndDate = labelEndDate;
	}



	public Area<?> getArea() {
		return area;
	}



	public void setArea(Area<?> area) {
		this.area = area;
	}



}
