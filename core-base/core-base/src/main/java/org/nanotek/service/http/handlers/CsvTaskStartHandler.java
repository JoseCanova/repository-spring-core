package org.nanotek.service.http.handlers;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nanotek.opencsv.CsvParsingTaskProvider;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.opencsv.service.CsvProcessorThreadRunner;
import org.nanotek.service.http.PostFunctionHandler;
import org.nanotek.service.http.TaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.util.concurrent.ListenableFutureTask;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Qualifier(value = "CsvTaskInitiatorHandler")
@RequestMapping(path={"/task"},produces = MediaType.APPLICATION_JSON_VALUE)
@TaskHandler
public class CsvTaskStartHandler<R extends CsvResult<?,?>> {

	PostFunctionHandler<TaskRequest , TaskStartResult> taskHandler = (taskKey) -> startTask(taskKey);

	@Autowired
	CsvParsingTaskProvider<?,?,?,?,R> csvProcessingCommandService;
	
	@Autowired
	CsvProcessorThreadRunner <?,?,?,?,R> csvProcessorThreadRunner;
	
	@PostMapping(path = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Callable<TaskStartResult> initializeTask(@RequestBody TaskRequest taskKey){
		return ()->taskHandler.apply(taskKey);
	}
	
	
	private TaskStartResult startTask(TaskRequest taskRequest) {

		 
		Thread t = new Thread(() ->{
			R result = null;
			do {
				Map<String,ListenableFutureTask<R>> tasks = csvProcessingCommandService.getListenableFutureTask();
				ListenableFutureTask<R> theTask = tasks.get(taskRequest.getTaskName());
				result = csvProcessorThreadRunner.startProcessorThread(theTask);
			}while(result!=null);
		});
		t.setDaemon(true);
		t.start();
		return new TaskStartResult();
		
	}
}
