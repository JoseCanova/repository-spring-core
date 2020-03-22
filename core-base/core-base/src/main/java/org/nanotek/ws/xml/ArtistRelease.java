package org.nanotek.ws.xml;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.nanotek.beans.entity.Release;
import org.nanotek.ws.WsBase;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="release", namespace="org.nanotek.ws.xml")
@SuppressWarnings("serial")
public class ArtistRelease implements WsBase{

	@XmlElement(name="releaseId")
	private Long id; 
	
	@XmlElement(name="artistReleaseName")
	private String name;

	public ArtistRelease()
	{ 
		
	}
	
	
	public ArtistRelease(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArtistRelease(Release release) {
		this.id = release.getId();
		try {
			this.name = new String (release.getName().getBytes() , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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

	@Override
	public String toString() {
		return "Release [id=" + id + ", name=" + name + "]";
	} 
	
	
}
