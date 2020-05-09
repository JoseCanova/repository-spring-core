package org.nanotek.beans.entity;

import java.util.UUID;

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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseWorkEntity;
import org.nanotek.entities.MutableGidEntity;
import org.nanotek.entities.MutableWorkCommentEntity;
import org.nanotek.entities.MutableWorkIdEntity;
import org.nanotek.entities.MutableWorkNameEntity;
import org.nanotek.entities.MutableWorkTypeEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid
@Indexed
@Entity
@Table(name="work" , 
indexes= {
		@Index(name="idx_work_id",columnList="work_id")
		},
uniqueConstraints = 
{@UniqueConstraint(name="uk_work_id",
columnNames = {"work_id"})})
public class Work<K extends Work<K>> extends  BrainzBaseEntity<K> 
implements BaseWorkEntity<K>,
MutableWorkIdEntity<Long>,
MutableGidEntity<UUID>,
MutableWorkNameEntity<String>,
MutableWorkTypeEntity<WorkType<?>>,
MutableWorkCommentEntity<WorkComment<?>>{
	
	private static final long serialVersionUID = 1277515257816058032L;

	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="work_id",nullable=false)
	public Long workId;
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@Column(name="gid", nullable=false , columnDefinition = "UUID NOT NULL")
	public UUID gid; 
	
	@NotNull(groups = {PrePersistValidationGroup.class})
	@ManyToOne(optional=false, fetch = FetchType.LAZY )
	public WorkType<?> workType; 


	@Field(name = "name" , index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String workName;
	
	@OneToOne(cascade = CascadeType.ALL , optional = true , fetch = FetchType.LAZY)
	@JoinTable(
			  name = "work_comment_join", 
			  joinColumns = @JoinColumn(name = "work_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id") )
	public WorkComment<?> workComment;

	public Work() {
		super();
	}

	@BrainzKey(entityClass = Work.class, pathName = "workId")
	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public UUID getGid() {
		return gid;
	}

	public void setGid(UUID gid) {
		this.gid = gid;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public WorkType<?> getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType<?> workType) {
		this.workType = workType;
	}

	public WorkComment<?> getWorkComment() {
		return workComment;
	}

	public void setWorkComment(WorkComment<?> workComment) {
		this.workComment = workComment;
	}
	
}
