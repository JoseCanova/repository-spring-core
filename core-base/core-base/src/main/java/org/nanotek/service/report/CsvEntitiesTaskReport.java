package org.nanotek.service.report;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.nanotek.opencsv.CsvResult;
import org.springframework.beans.factory.annotation.Autowired;

@ReportPresenter
public class CsvEntitiesTaskReport {

	@Autowired
	private CsvTaskOutcomeReportService<CsvResult<?,?>> taskReportService;
	
	Presentable<Map<String,?>[] , List<?>> presenter = (tasks) -> presentTasks(tasks);

	public List<?> generateTasklist() {
		return presentTasks(taskReportService.getBaseTypeTasks(),taskReportService.getRegularTasks());
	}
	
	@SafeVarargs
	private List<?> presentTasks(Map<String, ?> ... tasks) {
		return List
				.of(convertToPresenter(tasks[0] , "BaseType"), 
						convertToPresenter(tasks[1], "RegularType"));
	}
	
	private Map<String,String> convertToPresenter(Map<String,?> taskMap,String type){
		return taskMap.entrySet().stream().map(e -> Map.entry(e.getKey(), type)).collect(Collectors.toMap(Entry::getKey,Entry::getValue));
	}
	
}
