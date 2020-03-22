package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.nanotek.entities.MutableRecordingAliasIdEntity;

@Entity
@DiscriminatorValue(value = "RecordingAlias")
public class RecordingAlias<K extends RecordingAlias<K>> extends LongIdSortName<K> 
implements MutableRecordingAliasIdEntity<Long>{

	public static final long serialVersionUID = -1952011326249338518L;

	@NotNull
	@Column(name = "recording_alias_id")
	public Long recordingAliasId;
	
	@Column(name="locale", length=1000)
	public String locale; 
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "recording_id")
	public Recording<?> recording; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="recording_type_id")
	public RecordingAliasType<?> type;

    @Column(name="begin_date_year")
	public Integer beginDateYear ;
    
    @Column(name="begin_date_month")
	public Integer beginDateMonth    ;
	
    @Column(name="begin_date_day")
	public Integer beginDateDay      ;

    @Column(name="end_date_year")
	public Integer endDateYear       ;
    
    @Column(name="end_date_month")
	public Integer endDateMonth      ;
    
    @Column(name="end_date_day")
	public Integer endDateDay        ; 
	
	public RecordingAlias() {
	}


	@Override
	public Long getRecordingAliasId() {
		return recordingAliasId;
	}

	@Override
	public void setRecordingAliasId(Long id) {
		this.recordingAliasId = id;
	}


	public String getLocale() {
		return locale;
	}


	public void setLocale(String locale) {
		this.locale = locale;
	}


	public Recording<?> getRecording() {
		return recording;
	}


	public void setRecording(Recording<?> recording) {
		this.recording = recording;
	}


	public RecordingAliasType<?> getType() {
		return type;
	}


	public void setType(RecordingAliasType<?> type) {
		this.type = type;
	}


	public Integer getBeginDateYear() {
		return beginDateYear;
	}


	public void setBeginDateYear(Integer beginDateYear) {
		this.beginDateYear = beginDateYear;
	}


	public Integer getBeginDateMonth() {
		return beginDateMonth;
	}


	public void setBeginDateMonth(Integer beginDateMonth) {
		this.beginDateMonth = beginDateMonth;
	}


	public Integer getBeginDateDay() {
		return beginDateDay;
	}


	public void setBeginDateDay(Integer beginDateDay) {
		this.beginDateDay = beginDateDay;
	}


	public Integer getEndDateYear() {
		return endDateYear;
	}


	public void setEndDateYear(Integer endDateYear) {
		this.endDateYear = endDateYear;
	}


	public Integer getEndDateMonth() {
		return endDateMonth;
	}


	public void setEndDateMonth(Integer endDateMonth) {
		this.endDateMonth = endDateMonth;
	}


	public Integer getEndDateDay() {
		return endDateDay;
	}


	public void setEndDateDay(Integer endDateDay) {
		this.endDateDay = endDateDay;
	}

}
