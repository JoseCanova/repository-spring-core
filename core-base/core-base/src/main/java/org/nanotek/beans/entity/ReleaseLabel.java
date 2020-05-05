package org.nanotek.beans.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseReleaseLabelEntity;
import org.nanotek.entities.MutableLabelReleaseEntity;
import org.nanotek.entities.MutableReleaseEntity;
import org.nanotek.entities.MutableReleaseLabelCatalogEntity;
import org.nanotek.entities.MutableReleaseLabelIdEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Entity
@Table(name="release_label",
indexes= {
@Index(name="idx_release_label_id",columnList="release_label_id")
},
uniqueConstraints = {
		@UniqueConstraint(name="uk_release_label_id",columnNames = {"release_label_id"})
})
public class ReleaseLabel<K extends ReleaseLabel<K>> 
extends  BrainzBaseEntity<K>
implements 
BaseReleaseLabelEntity<K>,
MutableReleaseLabelIdEntity<Long>,
MutableLabelReleaseEntity<Label<?>>,
MutableReleaseLabelCatalogEntity<ReleaseLabelCatalog<?>>,
MutableReleaseEntity<Release<?>>{

	private static final long serialVersionUID = -4336246677898584112L;
	
	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="release_label_id",nullable = false)
	public Long releaseLabelId;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(fetch = FetchType.LAZY,optional = false , cascade = {CascadeType.MERGE})
	public Release<?> release; 
			
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(optional = false)
	@JoinTable(
			  name = "releaselable_label_join", 
			  joinColumns = @JoinColumn(name = "release_label_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "label_id",referencedColumnName = "id"))
	public Label<?> labelRelease; 
	
	@OneToOne(optional = true , orphanRemoval = true , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinTable(
			  name = "release_catalog_join", 
			  joinColumns = @JoinColumn(name = "release_label_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "catalog_id",referencedColumnName = "id"))
	public ReleaseLabelCatalog<?> releaseLabelCatalog;

	public ReleaseLabel() {
		super();
	}
	
	public ReleaseLabel(@NotNull(groups = { CsvValidationGroup.class, Default.class }) Long releaseLabelId) {
		super();
		this.releaseLabelId = releaseLabelId;
	}

	@BrainzKey(entityClass = ReleaseLabel.class,pathName = "releaseLabelId")
	public Long getReleaseLabelId() {
		return releaseLabelId;
	}

	public void setReleaseLabelId(Long releaseLabelId) {
		this.releaseLabelId = releaseLabelId;
	}

	public Label<?> getLabelRelease() {
		return labelRelease;
	}

	public void setLabelRelease(Label<?> labelRelease) {
		this.labelRelease = labelRelease;
	}

	public ReleaseLabelCatalog<?> getReleaseLabelCatalog() {
		return releaseLabelCatalog;
	}

	public void setReleaseLabelCatalog(ReleaseLabelCatalog<?> releaseLabelCatalog) {
		this.releaseLabelCatalog = releaseLabelCatalog;
	}

	public Release<?> getRelease() {
		return release;
	}

	public void setRelease(Release<?> release) {
		this.release = release;
	} 
	
}
