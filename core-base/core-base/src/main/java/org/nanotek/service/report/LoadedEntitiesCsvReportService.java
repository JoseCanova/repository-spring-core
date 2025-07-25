package org.nanotek.service.report;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.nanotek.opencsv.service.LoadedEntitiesReport;
import org.springframework.beans.factory.annotation.Autowired;

@ReportPresenter
public class LoadedEntitiesCsvReportService {

	Presentable<List<LoadedEntitiesReport>,String> presenter = (theList) -> convertToCsv(theList);
	
	private DatabaseEntityLoadReportService<?,?,?,?,?,?> databaseEntityLoadReportService;
	
	@Autowired
	public LoadedEntitiesCsvReportService(@NotNull DatabaseEntityLoadReportService<?,?,?,?,?,?> databaseEntityLoadReportService2) {
		this.databaseEntityLoadReportService = databaseEntityLoadReportService2;
	}
	
	public String generateReport() {
		return convertToCsv(databaseEntityLoadReportService
				.generateLoadReport());
	}
	
	private String convertToCsv(List<LoadedEntitiesReport> theList) {
		return  theList
					.stream()
					.map(er -> er.entity().getSimpleName().concat("\t").concat(er.value().toString()))
					.reduce((a,b) -> a.concat("\n").concat(b)).orElse("");
	}
}
