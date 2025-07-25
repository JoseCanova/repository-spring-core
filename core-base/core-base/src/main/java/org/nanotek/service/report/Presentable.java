package org.nanotek.service.report;

import java.util.function.Function; // Import for the Function interface

import org.nanotek.TagInterface;
import org.nanotek.opencsv.service.LoadedEntitiesReport; 

/**
 * A functional interface that defines the contract for classes capable of
 * transforming a raw list of {@link LoadedEntitiesReport} into a specific,
 * consumer-ready presentation format.
 *
 * <p>This interface now explicitly declares the type of the internal transformation
 * {@link Function} that it utilizes, making the transformation mechanism explicit
 * as part of its contract. The {@code present} method provides a default implementation
 * by applying this internal transformer function.</p>
 *
 * <p>Implementations of this interface act as "Presenters," taking the core
 * report data and converting it into various output representations such as
 * formatted strings, JSON, HTML, CSV text, or even binary report documents.</p>
 *
 * <p>Classes implementing this interface are typically {@link ReportPresenter @ReportPresenter}
 * components within the application's service layer, bridging the gap between
 * data generation and data consumption by external systems or UI.</p>
 *
 * @param <R> The type of the transformed, presented report output (e.g., String, Map, byte[], custom DTO).
 * @param <T> The type of the internal transformation function, which must extend
 * {@link Function} and be capable of transforming a {@code List<LoadedEntitiesReport>} to {@code R}.
 */
@TagInterface
@FunctionalInterface 
public interface Presentable<T,R> extends Function<T,R> {
}