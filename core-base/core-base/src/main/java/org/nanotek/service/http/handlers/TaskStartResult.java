package org.nanotek.service.http.handlers;

import javax.json.bind.annotation.JsonbProperty;

public class TaskStartResult {

	private String startResult = "started";
	
	@JsonbProperty(value = "result")
	public String getStartResult() {
		return startResult;
	}

	public TaskStartResult() {
		
	}
	
}
