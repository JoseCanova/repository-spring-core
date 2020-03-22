package org.nanotek.beans.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.nanotek.entities.BaseRecordingLengthEntity;
import org.nanotek.entities.MutableRecordingEntity;

@Entity
@Table(name="recording_lengthy_base",
						indexes= {
								@Index(unique = false , name = "long_length_table_idx" , columnList ="length")
							})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "table_id",
	    columnDefinition = "VARCHAR NOT NULL"
	)
@DiscriminatorValue(value="RecordingLength")
public class RecordingLength<K extends RecordingLength<K>> extends LongLengthyBase<K> implements 
BaseRecordingLengthEntity<K>,
MutableRecordingEntity<Recording<?>>{
	
	private static final long serialVersionUID = -8708909035267715010L;
	
	@NotNull
	@OneToOne(fetch = FetchType.LAZY , mappedBy = "recordingLenght")
	private Recording<?> recording;
	
	public RecordingLength() {}
	
	
	public RecordingLength(@NotNull Long length) {
		super(length);
	}

	public RecordingLength(Recording<?> recording, @NotNull Long length) {
		super(length);
		this.recording = recording;
	}

	public Recording<?> getRecording() {
		return recording;
	}

	public void setRecording(Recording<?> recording) {
		this.recording = recording;
	}

}
