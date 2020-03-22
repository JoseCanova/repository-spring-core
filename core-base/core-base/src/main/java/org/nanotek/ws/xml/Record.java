package org.nanotek.ws.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.nanotek.ws.WsBase;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="record" , namespace="org.nanotek.ws.xml")
public class Record implements WsBase{

	@XmlElement(name="recordId")
	private Long id;

	@XmlElement(name="recordName")
	private String recordName;

	@XmlElement(name="artistCreditId")
	private Long artistCreditId; 
	
	private List<Track> tracks = new ArrayList<Track>(); 
	
	public Record()
	{
	}

	public Record(Long id, String recordName) {
		super();
		this.id = id;
		this.recordName = recordName;
	}

	public Record(Long id, String recordName, Long artistCreditId) {
		super();
		this.id = id;
		this.recordName = recordName;
		this.artistCreditId = artistCreditId;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public Long getArtistCreditId() {
		return artistCreditId;
	}

	public void setArtistCreditId(Long artistCreditId) {
		this.artistCreditId = artistCreditId;
	}

	public List<Track> getTracks() {
		return Optional.ofNullable(tracks).orElse(new ArrayList<Track>());
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", recordName=" + recordName
				+ ", artistCreditId=" + artistCreditId + ", tracks=" + tracks
				+ "]";
	}


}
