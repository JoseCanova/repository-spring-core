package org.nanotek.service.http;

import java.util.function.Consumer;

/**
 * {@code DeleteConsumerHandler} is a functional interface that abstracts the logic
 * for HTTP DELETE methods. It extends {@link java.util.function.Consumer Consumer&lt;T&gt;}
 * to model operations that consume an input of type {@code T} (typically an identifier
 * for the resource to be deleted) and perform an action, without producing a direct
 * return value in terms of a resource. DELETE operations often result in a status code
 * (e.g., 200 OK, 204 No Content) rather than a response body containing data.
 *
 * <p>This handler helps in isolating the resource deletion logic from the controller's
 * HTTP concerns, aligning with the Single Responsibility Principle (SRP). It allows
 * for more focused, reusable, and testable components for handling deletion requests.</p>
 *
 * @param <T> The type of the input argument to the DELETE handler, typically an identifier
 * (e.g., Long, String) or a DTO containing parameters necessary to identify the
 * resource(s) to be deleted.
 * @see org.nanotek.service.http.PresentationHandler
 * @see java.util.function.Consumer
 */
public interface DeleteConsumerHandler<T> extends Consumer<T> {
    // The accept(T t) method is inherited from Consumer<T>
}