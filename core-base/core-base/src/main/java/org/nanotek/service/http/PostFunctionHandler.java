package org.nanotek.service.http;

import java.util.function.Function;

/**
 * {@code PostFunctionHandler} is a functional interface that abstracts the logic
 * for HTTP POST methods. It extends {@link java.util.function.Function Function&lt;T, R&gt;}
 * to model operations that take an input of type {@code T} (representing the request body or parameters)
 * and produce a result of type {@code R} (typically the newly created resource or a confirmation).
 *
 * <p>This handler promotes a clean separation between the HTTP-specific concerns of a controller
 * and the domain-level logic of creating or submitting data. It embodies the Single Responsibility Principle (SRP)
 * by encapsulating the transformation from request payload to domain result. This approach enhances
 * modularity, reusability, and testability of the POST logic.</p>
 *
 * @param <T> The type of the input argument to the POST handler, typically a DTO representing
 * the request body or form parameters used to create a new resource.
 * @param <R> The type of the result produced by the POST handler, often the representation of
 * the newly created resource, its identifier, or a status object.
 * @see org.nanotek.service.http.PresentationHandler
 * @see java.util.function.Function
 */
public interface PostFunctionHandler<T, R> extends Function<T, R> {
    // The apply(T t) method is inherited from Function<T, R>
}