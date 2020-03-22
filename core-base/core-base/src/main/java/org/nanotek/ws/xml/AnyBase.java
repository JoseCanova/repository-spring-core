package org.nanotek.ws.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.nanotek.ws.WsBase;

@XmlRootElement(name="root" , namespace="org.nanotek.ws.xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnyBase {

	@XmlAnyElement
	List<WsBase> xmlMessage = new ArrayList<>();

	public List<WsBase> getXmlMessage() {
		return Optional.ofNullable(xmlMessage).orElseGet(ArrayList::new);
	}

	public void setXmlMessage(List<WsBase> xmlMessage) {
		this.xmlMessage = xmlMessage;
	}

}

