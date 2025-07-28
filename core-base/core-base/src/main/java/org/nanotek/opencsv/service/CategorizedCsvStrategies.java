package org.nanotek.opencsv.service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull; // Assuming this is the intended import

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;

/**
 * A concrete class to hold the categorized CSV strategies.
 * Implements CategorizedCsvStrategiesAccessor to provide a clear and type-safe way
 * to return both basetype and regular strategies.
 */
public class CategorizedCsvStrategies<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>>
implements CategorizedCsvStrategiesAccessor<T,S,P,M> {

    protected Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> basetypeStrategies;
    protected Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> regularStrategies;

    public CategorizedCsvStrategies(
        @NotNull Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> basetypeStrategies,
        @NotNull Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> regularStrategies)
    {
        this.basetypeStrategies = basetypeStrategies;
        this.regularStrategies = regularStrategies;
    }

    @Override
    public Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> basetypeStrategies(){
        return Optional.ofNullable(basetypeStrategies).orElseThrow(() -> new IllegalStateException("Basetype strategies map is null."));
    }

    @Override
    public Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> regularStrategies(){
        return Optional.ofNullable(regularStrategies).orElseThrow(() -> new IllegalStateException("Regular strategies map is null."));
    }

	@Override
	public Map<String, CsvFileItemConcreteStrategy<T, S, P, M>> allStrategies() {
		Optional.ofNullable(basetypeStrategies).orElseThrow(() -> new IllegalStateException("Basetype strategies map is null."));
		Optional.ofNullable(regularStrategies).orElseThrow(() -> new IllegalStateException("Regular strategies map is null."));
		return   Stream.concat(basetypeStrategies.entrySet().parallelStream(), regularStrategies.entrySet().parallelStream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> newValue // Merge function: if key duplicates, take the newValue (from map2)
                ));
	}

}