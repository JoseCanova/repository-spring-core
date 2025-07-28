package org.nanotek.service.http;

import java.util.function.Function;

/**
 * {@code PutFunctionHandler} is a functional interface that abstracts the logic
 * for HTTP PUT methods. It extends {@link java.util.function.Function Function&lt;T, R&gt;}
 * to model operations that take an input of type {@code T} (representing the request body or parameters
 * for updating a resource) and produce a result of type {@code R} (typically the updated resource
 * or a status confirming the modification).
 *
 * <p>Similar to {@code PostFunctionHandler}, this interface decouples the update logic from
 * the HTTP layer, allowing controllers to focus on request mapping while the actual
 * resource update and transformation are handled by dedicated functional components.
 * This design supports the Single Responsibility Principle (SRP) and improves the
 * reusability and testability of update operations.</p>
 *
 * @param <T> The type of the input argument to the PUT handler, typically a DTO representing
 * the request body or path parameters used to update an existing resource.
 * @param <R> The type of the result produced by the PUT handler, often the representation of
 * the updated resource, its identifier, or a confirmation status.
 * @see org.nanotek.service.http.PresentationHandler
 * @see java.util.function.Function
 */
public interface PutFunctionHandler<T, R> extends Function<T, R> {
    // The apply(T t) method is inherited from Function<T, R>
}