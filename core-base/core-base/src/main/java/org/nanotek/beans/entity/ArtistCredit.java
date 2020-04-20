package org.nanotek.beans.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.nanotek.PrePersistValidationGroup;
import org.nanotek.annotations.BrainzKey;
import org.nanotek.entities.BaseArtistCreditEntity;
import org.nanotek.entities.MutableArtistCreditCountEntity;
import org.nanotek.entities.MutableArtistCreditIdEntity;
import org.nanotek.entities.MutableArtistCreditNameEntity;
import org.nanotek.entities.MutableArtistCreditRefCountEntity;
import org.nanotek.entities.MutableArtistListEntity;
import org.nanotek.entities.MutableRecordingSetEntity;
import org.nanotek.entities.MutableReleaseSetEntity;
import org.nanotek.opencsv.CsvValidationGroup;

@Valid
@Entity
@Table(name="artist_credit", indexes= {
		@Index(name="idx_artist_credit_id",columnList="artist_credit_id")
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
					attributeNodes = {@NamedAttributeNode(value="recordingLength" , subgraph = "recordingLength")}
))
public class ArtistCredit<K extends ArtistCredit<K>> extends 
BrainzBaseEntity<K> implements  
BaseArtistCreditEntity<K>,
MutableArtistCreditIdEntity<Long>,	
MutableArtistCreditCountEntity<ArtistCreditCount<?>>, 
MutableArtistCreditRefCountEntity<ArtistCreditRefCount<?>>,
MutableRecordingSetEntity<Recording<?>>,
MutableReleaseSetEntity<Release<?>>,
MutableArtistListEntity<Artist<?>>,
MutableArtistCreditNameEntity<String>
{
	
	private static final long serialVersionUID = -3086006757943654550L;
	
	@NotNull(groups = {CsvValidationGroup.class,PrePersistValidationGroup.class})
	@Column(name="artist_credit_id" , nullable=false)
	public Long artistCreditId;
	
	@NotBlank(groups = {PrePersistValidationGroup.class})
	@Column(name="name" , nullable=false, columnDefinition = "VARCHAR NOT NULL")
	public String artistCreditName;


	@Override
	public String getArtistCreditName() {
		return artistCreditName;
	}

	@Override
	public void setArtistCreditName(String k) {
		this.artistCreditName = k;
	}
	
	@NotNull(groups= {PrePersistValidationGroup.class})
	@OneToOne(optional=false,cascade = CascadeType.ALL)
	@JoinTable(name="artist_credit_count_join",
		inverseJoinColumns={@JoinColumn(name="artist_count_id", referencedColumnName="id") },
		joinColumns={ @JoinColumn(name="artist_credit_id", referencedColumnName="id") })
	public ArtistCreditCount<?> artistCreditCount; 
	
	@NotNull(groups= {PrePersistValidationGroup.class})
	@OneToOne(optional=false , cascade = CascadeType.ALL)
	@JoinTable(name="artist_ref_count_join",
		inverseJoinColumns={@JoinColumn(name="artist_refcount_id", referencedColumnName="id") },
		joinColumns={ @JoinColumn(name="artist_credit_id", referencedColumnName="id") })
	public ArtistCreditRefCount<?> artistCreditRefCount;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="artistCredit",cascade = CascadeType.PERSIST)
	public Set<Release<?>> releases; 

	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
	@JoinTable(name="artist_credit_name_rel",
	inverseJoinColumns={@JoinColumn(name="artist_name_id", referencedColumnName="id") },
	joinColumns={ @JoinColumn(name="artist_credit_id", referencedColumnName="id") })
	public List<Artist<?>> artists;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "artistCredit",cascade = CascadeType.PERSIST)
	public Set<Recording<?>> recordings; 
	
	public ArtistCredit() {}
	
	public ArtistCredit(@NotNull Long id , @NotBlank String name, @NotNull ArtistCreditCount<?> artistCount,
			@NotNull ArtistCreditRefCount<?> refCount, Set<Recording<?>> recordings) {
		this.artistCreditName = name;
		this.artistCreditId = id;
		this.artistCreditCount = artistCount;
		this.artistCreditRefCount = refCount;
		this.recordings = recordings;
	}

	@BrainzKey(entityClass = ArtistCredit.class, pathName = "artistCreditId")
	public Long getArtistCreditId() {
		return artistCreditId;
	}

	public void setArtistCreditId(Long artistCreditId) {
		this.artistCreditId = artistCreditId;
	}

	public ArtistCreditCount<?> getArtistCreditCount() {
		return artistCreditCount;
	}

	public void setArtistCreditCount(ArtistCreditCount<?> artistCreditCount) {
		this.artistCreditCount = artistCreditCount;
	}

	public ArtistCreditRefCount<?> getArtistCreditRefCount() {
		return artistCreditRefCount;
	}

	public void setArtistCreditRefCount(ArtistCreditRefCount<?> artistCreditRefCount) {
		this.artistCreditRefCount = artistCreditRefCount;
	}

	public Set<Release<?>> getReleases() {
		return releases;
	}

	public void setReleases(Set<Release<?>> releases) {
		this.releases = releases;
	}

	public List<Artist<?>> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist<?>> artists) {
		this.artists = artists;
	}

	public Set<Recording<?>> getRecordings() {
		return recordings;
	}

	public void setRecordings(Set<Recording<?>> recordings) {
		this.recordings = recordings;
	}



	
}
