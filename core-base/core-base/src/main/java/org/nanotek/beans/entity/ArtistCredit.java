package org.nanotek.beans.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.BaseEntity;
import org.nanotek.entities.BaseArtistCreditEntity;
import org.nanotek.entities.MutableArtistCreditCountEntity;
import org.nanotek.entities.MutableArtistCreditIdEntity;
import org.nanotek.entities.MutableArtistCreditRefCountEntity;

@Entity
@Table(name="artist_credit", uniqueConstraints= {
		@UniqueConstraint(name="uk_artist_credit_id",columnNames={"artist_credit_id"})
		})
@NamedQueries(value = { 
		@NamedQuery(name = "FindArtistCreditById", query ="Select a from ArtistCredit a where a.id = :id"),
		@NamedQuery(name = "ArtistCreditLoadAll", query ="Select a from ArtistCredit a order by a.id asc"),
		@NamedQuery(name="FindArtistCredits" , query = "Select a from ArtistCredit a where a.id in (:ids)"),
		@NamedQuery(name="ArtistCredit.findByArtistCreditId" , query="Select a from ArtistCredit a left outer join a.releases where a.id = :id")
})
@NamedEntityGraph( name = "fetch.ArtistCredit.recordings",
					attributeNodes = {@NamedAttributeNode(value="recordings",subgraph = "recordings")},
					subgraphs = @NamedSubgraph(name = "recordings", 
					attributeNodes = {@NamedAttributeNode(value="recordingLenght" , subgraph = "recordingLenght")}
))
public class ArtistCredit<K extends ArtistCredit<K>> extends 
LongIdName<K> implements  
BaseArtistCreditEntity<K>,
MutableArtistCreditIdEntity<Long>,	
MutableArtistCreditCountEntity<ArtistCreditCount<?>>, 
MutableArtistCreditRefCountEntity<ArtistCreditRefCount> {
	
	private static final long serialVersionUID = -3086006757943654550L;
	
	@NotNull
	@Column(name="artist_credit_id" , nullable=false)
	public Long artistCreditId;
	
	
	
	@NotNull
	@OneToOne(optional=false)
	@JoinTable(name="artist_credit_count_join",
		inverseJoinColumns={@JoinColumn(name="artist_count_id", referencedColumnName="id") },
		joinColumns={ @JoinColumn(name="artist_credit_id", referencedColumnName="id") })
	public ArtistCreditCount<?> artistCreditCount; 
	
	@NotNull
	@OneToOne(optional=false)
	@JoinTable(name="artist_ref_count_join",
		inverseJoinColumns={@JoinColumn(name="artist_refcount_id", referencedColumnName="id") },
		joinColumns={ @JoinColumn(name="artist_credit_id", referencedColumnName="id") })
	public ArtistCreditRefCount artistCreditRefCount;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="artistCredit")
	public Set<Release> releases; 

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="artist_credit_name_rel",
	inverseJoinColumns={@JoinColumn(name="artist_name_id", referencedColumnName="id") },
	joinColumns={ @JoinColumn(name="artist_credit_id", referencedColumnName="id") })
	public List<Artist<?>> artists;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="artistCredit")
	public Set<Recording<?>> recordings; 
	
	public ArtistCredit() {}
	
	public ArtistCredit(@NotNull Long id , @NotBlank String name, @NotNull ArtistCreditCount<?> artistCount,
			@NotNull ArtistCreditRefCount refCount, Set<Recording<?>> recordings) {
		super(name);
		this.artistCreditId = id;
		this.artistCreditCount = artistCount;
		this.artistCreditRefCount = refCount;
		this.recordings = recordings;
	}

	@Override
	public Long getArtistCreditId() {
		return artistCreditId;
	}

	@Override
	public ArtistCreditCount<?> getArtistCreditCount() {
		return artistCreditCount;
	}

	@Override
	public ArtistCreditRefCount getArtistCreditRefCount(ArtistCreditRefCount k) {
		return artistCreditRefCount;
	}

	@Override
	public void setArtistCreditRefCount(ArtistCreditRefCount k) {
		this.artistCreditRefCount = k;
	}

	@Override
	public void setArtistCreditCount(ArtistCreditCount<?> k) {
		this.artistCreditCount = k;
	}

	@Override
	public void setArtistCreditId(Long k) {
		this.artistCreditId = k;
	}

}
