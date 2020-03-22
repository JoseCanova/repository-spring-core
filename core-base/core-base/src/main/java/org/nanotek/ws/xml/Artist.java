package org.nanotek.ws.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.nanotek.ws.WsBase;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="artist" , namespace="org.nanotek.ws.xml")
@SuppressWarnings("serial")
public class Artist implements WsBase{

	@XmlElement(name="id")
	private Long id; 

	@XmlElement(name="name")
	private String name; 
	
	@XmlElement(name="credits")
	private List<Credit> credits = null; 
	
	@XmlElement(name="releases")
	private List<ArtistRelease> releases = null;
	
	@XmlElement(name="records")
	private List<Record> records = null;
	
	
	public Artist() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Credit> getCredits() {
		return credits =  Optional.ofNullable(credits).orElse(new ArrayList<Credit>());
	}

	public void setCredits(List<Credit> credits) {
		this.credits = credits;
	}

	public List<ArtistRelease> getReleases() {
		return releases =  Optional.ofNullable(releases).orElse(new ArrayList<ArtistRelease>());
	}

	public void setReleases(List<ArtistRelease> releases) {
		this.releases = releases;
	}

	public List<Record> getRecords() {
		return records =  Optional.ofNullable(records).orElse(new ArrayList<Record>());
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", credits=" + credits
				+ ", releases=" + releases + ", records=" + records + "]";
	}

}
