package org.nanotek.opencsv.service;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;

import java.util.Map;

/**
 * Interface defining accessors for categorized CSV strategies.
 * This allows for a contract-based approach to retrieving basetype and regular strategies.
 */
public interface CategorizedCsvStrategiesAccessor<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>> {

    Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> basetypeStrategies();
    Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> regularStrategies();

}