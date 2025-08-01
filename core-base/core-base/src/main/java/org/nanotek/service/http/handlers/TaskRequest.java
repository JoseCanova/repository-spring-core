package org.nanotek.service.http.handlers;

import javax.json.bind.annotation.JsonbProperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequest {

	private String taskName;
	
	public TaskRequest() {}
	
	@JsonCreator
	public TaskRequest(@JsonbProperty(value="taskName") String taskName2) {
		this.taskName = taskName2;
	}


	@JsonbProperty(value="taskName")
	public String getTaskName() {
		return taskName;
	}



	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

		
}
