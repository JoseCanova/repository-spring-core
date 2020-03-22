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

@Entity
@Table(name="instrument", 
uniqueConstraints= {
@UniqueConstraint(name="uk_instrument_id",columnNames={"instrument_id"})
})
public class Instrument<E extends Instrument<E>> extends LongIdGidName<E>  {

	private static final long serialVersionUID = 1720965406197902687L;
	
	@Column(name="instrument_id" , nullable=false)
	private Long instrumentId; 

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY , optional = false )
	private InstrumentType<?> type; 
	
	@OneToOne(optional=true)
	@JoinTable(
			  name = "instrument_comment_join", 
			  joinColumns = @JoinColumn(name = "instrument_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id"))
	private InstrumentComment<?> comment;
	
	@OneToOne(optional=true)
	@JoinTable(
			  name = "description_comment_join", 
			  joinColumns = @JoinColumn(name = "instrument_id" , referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "description_id",referencedColumnName = "id"))
	private InstrumentDescription description;
	
	public Instrument() {
	}
	
	public Instrument(  @NotNull Long id,  
						@NotBlank @Size(min = 1, max = 50) String gid, 
						@NotBlank String name,
						@NotNull InstrumentType<?> type) {
		super(gid,name);
		this.type = type;
		this.instrumentId = id;
	}

	public Instrument(
						@NotNull Long id, 
						@NotBlank @Size(min = 1, max = 50) String gid, 
						@NotBlank String name,
						@NotNull InstrumentType<?> type, 
						InstrumentComment<?> comment, 
						InstrumentDescription description) {
		super(gid,name);
		this.type = type;
		this.comment = comment;
		this.description = description;
		this.instrumentId = id;
	}

	public InstrumentComment<?> getComment() {
		return comment;
	}

	public void setComment(InstrumentComment<?> comment) {
		this.comment = comment;
	}

	public InstrumentDescription getDescription() {
		return description;
	}

	public void setDescription(InstrumentDescription description) {
		this.description = description;
	}

	public InstrumentType getType() {
		return type;
	}

	public void setType(InstrumentType type) {
		this.type = type;
	}

}
