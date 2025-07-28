package org.nanotek.service.http;

import java.util.function.Function;

/**
 * {@code GetFunctionHandler} is a functional interface designed to abstract the logic
 * for HTTP GET methods that require input arguments and produce a result.
 * It extends {@link java.util.function.Function Function&slt;T, R&gt;}
 * to represent a clear transformation from an input of type {@code T} to an output of type {@code R}.
 *
 * <p>This interface is a specialized form of a "handler" within the presentation layer,
 * particularly useful when GET requests involve path variables, query parameters,
 * or other forms of input that influence the data retrieval or processing.</p>
 *
 * <p>It complements {@link GetHandler} (which models GET methods without input arguments)
 * by providing a robust mechanism for handling more complex GET operations.
 * By using {@code GetFunctionHandler}, controllers can delegate the specific input-to-output
 * transformation logic, thereby enhancing modularity, testability, and adherence to
 * the Single Responsibility Principle (SRP) in web application design.</p>
 *
 * @param <T> The type of the input argument to the GET handler, typically representing
 * request parameters, path variables, or a DTO encapsulating such inputs.
 * @param <R> The type of the result produced by the GET handler, usually a data structure
 * intended for presentation (e.g., a DTO, a List of results).
 * @see org.nanotek.service.http.PresentationHandler
 * @see org.nanotek.service.http.GetHandler
 * @see java.util.function.Function
 */
public interface GetFunctionHandler<T, R> extends Function<T, R> {
    // The apply(T t) method is inherited from Function<T, R>
}