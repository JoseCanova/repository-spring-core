package org.nanotek.beans.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseLabelEntity;
import org.nanotek.entities.MutableAreaEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableLabelBeginDateEntity;
import org.nanotek.entities.MutableLabelCodeEntity;
import org.nanotek.entities.MutableLabelEndDateEntity;
import org.nanotek.entities.MutableLabelTypeEntity;
import org.nanotek.entities.MutableNameEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="label")
public class Label<K extends Label<K>> 
extends SortNameBase<K>
implements BaseLabelEntity<K>,
MutableGidEntity<UUID>,MutableNameEntity<String>,
MutableLabelBeginDateEntity<LabelBeginDate<?>>,
MutableLabelEndDateEntity<LabelEndDate<?>>,
MutableLabelTypeEntity<LabelType<?>>,
MutableAreaEntity<Area<?>>,
MutableLabelCodeEntity<Integer>{
	
	@Column
	private Long labelId;

	@NotNull
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	protected UUID gid;
	
	@NotNull
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String name;

	@ManyToOne
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
		super(sortName);
		this.labelId = labelId;
		this.gid = gid;
		this.name = name;
		this.labelType = labelType;
	}
	


	public Label(@NotBlank String sortName) {
		super(sortName);
	}

	public Label() {
	}



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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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
