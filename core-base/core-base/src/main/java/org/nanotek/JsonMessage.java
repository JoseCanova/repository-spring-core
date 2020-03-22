package org.nanotek;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonMessage<K extends IdBase<K,ID>,ID extends Serializable> implements ImmutableBase<K,ID>{

	private static final long serialVersionUID = 6737835842589884724L;

	private ID id;
	
	private String response; 
	
	public JsonMessage() {
	}
	
	public JsonMessage(ID id) {
		super();
		this.id = id;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public ID getId() {
		return id;
	}
	
}
