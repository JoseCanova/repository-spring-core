package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue(value = "RecordingAliasSortName")
public class RecordingAliasSortName<K extends  RecordingAliasSortName<K>> extends SortNameBase<K> {
	
	private static final long serialVersionUID = -6058110797084324692L;
	
	@OneToOne(mappedBy = "recordingAliasSortName")
	public RecordingAlias<?> recordingAlias;
	
	public RecordingAliasSortName(@NotBlank String sortName) {
		super(sortName);
	}

	public RecordingAliasSortName() {
	}

	public RecordingAlias<?> getRecordingAlias() {
		return recordingAlias;
	}

	public void setRecordingAlias(RecordingAlias<?> recordingAlias) {
		this.recordingAlias = recordingAlias;
	}

}
