package org.nanotek.ws.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.nanotek.ws.WsBase;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="credit", namespace="org.nanotek.ws.xml")
@SuppressWarnings("serial")
public class Credit implements WsBase{

	@XmlElement(name="creditId")
	private Long id;

	@XmlElement(name="creditName")
	private String creditName; 
	
	@XmlElement(name="count")
	private Long creditCount; 
	
	public Credit()
	{ 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public Long getCreditCount() {
		return creditCount;
	}

	public void setCreditCount(Long creditCount) {
		this.creditCount = creditCount;
	}

	@Override
	public String toString() {
		return "Credit [id=" + id + ", creditName=" + creditName
				+ ", creditCount=" + creditCount + "]";
	}
	

}
