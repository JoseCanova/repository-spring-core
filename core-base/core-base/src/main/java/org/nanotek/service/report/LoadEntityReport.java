package org.nanotek.service.report;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.validation.constraints.NotNull;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.nanotek.opencsv.service.CsvStrategyCategorizer;
import org.nanotek.opencsv.service.LoadedEntitiesReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

@ReportPresenter
public class LoadEntityReport<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>> {

	Presentable<String , LoadedEntitiesReport> presenter = (entityKey) -> returnLoadEntityReport(entityKey);

	private @NotNull @Autowired 
	CsvStrategyCategorizer<T,S,P,M> csvStrategyCategorizer;

	private @NotNull DatabaseEntityLoadReportService<T,S,P,M, ?, ?> databaseEntityLoadReportService;

	@Autowired
	public LoadEntityReport(@NotNull DatabaseEntityLoadReportService<T,S,P,M,?,?> databaseEntityLoadReportService2, 
			@NotNull
			CsvStrategyCategorizer<T,S,P,M> csvStrategyCategorizer2) {
		this.databaseEntityLoadReportService = databaseEntityLoadReportService2;
		this.csvStrategyCategorizer = csvStrategyCategorizer2;
	}

	@Async
	public CompletableFuture<LoadedEntitiesReport> loadEntityReport(String entityKey) {
		return CompletableFuture.supplyAsync(() -> presenter.apply(entityKey));
	}

	private LoadedEntitiesReport returnLoadEntityReport(String entityKey) {
		Map<String , CsvFileItemConcreteStrategy<T,S,P,M>> strategies = csvStrategyCategorizer.categorizeStrategies().allStrategies();
		CsvFileItemConcreteStrategy<T,S,P,M> strategy = strategies.get(entityKey);
		M baseBean = Base.newInstance(strategy.getImmutable()).orElseThrow();
		Class<?> baseClass = baseBean.getBaseClass();
		return databaseEntityLoadReportService.generateLoadReport().parallelStream().filter(c -> c.entity().equals(baseClass)).findFirst().orElseThrow();

	}


}
