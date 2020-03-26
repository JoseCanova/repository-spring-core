package org.nanotek.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.nanotek.entities.BaseInstrumentEntity;
import org.nanotek.entities.MutableInstrumentCommentEntity;
import org.nanotek.entities.MutableInstrumentDescriptionEntity;
import org.nanotek.entities.MutableInstrumentIdEntity;
import org.nanotek.entities.MutableInstrumentTypeEntity;

@Entity
@Table(name="instrument", 
uniqueConstraints= {
@UniqueConstraint(name="uk_instrument_id",columnNames={"instrument_id"})
})
public class Instrument<E extends Instrument<E>> extends LongIdGidName<E>  
implements BaseInstrumentEntity,
MutableInstrumentIdEntity<Long>,
MutableInstrumentTypeEntity<InstrumentType<?>>,
MutableInstrumentCommentEntity<InstrumentComment<?>>,
MutableInstrumentDescriptionEntity<InstrumentDescription<?>>{

	private static final long serialVersionUID = 1720965406197902687L;
	
	@Column(name="instrument_id" , nullable=false)
	private Long instrumentId; 

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY , optional = false )
	private InstrumentType<?> instrumentType; 
	

	@OneToOne(optional=true)
	@JoinTable(
			  name = "instrument_comment_join", 
			  joinColumns = @JoinColumn(name = "instrument_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id"))
	private InstrumentComment<?> instrumentComment;
	
	@OneToOne(optional=true)
	@JoinTable(
			  name = "instrument_description_join", 
			  joinColumns = @JoinColumn(name = "instrument_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "description_id",referencedColumnName = "id"))
	private InstrumentDescription<?> instrumentDescription;
	
	public Instrument() {
	}
	
	public Instrument(  @NotNull Long id,  
						@NotBlank @Size(min = 1, max = 50) String gid, 
						@NotBlank String name,
						@NotNull InstrumentType<?> type) {
		super(gid,name);
		this.instrumentType = type;
		this.instrumentId = id;
	}

	public Instrument(
						@NotNull Long id, 
						@NotBlank @Size(min = 1, max = 50) String gid, 
						@NotBlank String name,
						@NotNull InstrumentType<?> type, 
						InstrumentComment<?> comment, 
						InstrumentDescription<?> description) {
		super(gid,name);
		this.instrumentType = type;
		this.instrumentComment = comment;
		this.instrumentDescription = description;
		this.instrumentId = id;
	}

	public Long getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(Long instrumentId) {
		this.instrumentId = instrumentId;
	}

	public InstrumentType<?> getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(InstrumentType<?> instrumentType) {
		this.instrumentType = instrumentType;
	}

	public InstrumentComment<?> getInstrumentComment() {
		return instrumentComment;
	}

	public void setInstrumentComment(InstrumentComment<?> instrumentComment) {
		this.instrumentComment = instrumentComment;
	}

	public InstrumentDescription<?> getInstrumentDescription() {
		return instrumentDescription;
	}

	public void setInstrumentDescription(InstrumentDescription<?> instrumentDescription) {
		this.instrumentDescription = instrumentDescription;
	}	

}
