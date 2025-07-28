package org.nanotek.service.http;
import java.util.function.Supplier;

/**
 * {@code GetHandler} is a functional interface that serves as an abstraction for HTTP GET method logic
 * within the presentation layer, adhering to the principles of Domain-Driven Design and SOLID.
 *
 * <p>By extending {@link java.util.function.Supplier Suppliers}, this interface models operations
 * that produce a result of type {@code Y} without taking any input arguments, which is characteristic
 * of many HTTP GET requests designed to retrieve data.</p>
 *
 * <p>This abstraction allows for a clear separation of concerns, decoupling the core logic
 * of retrieving and preparing data from the specifics of Spring Web MVC controller methods.
 * It promotes the Single Responsibility Principle (SRP) by enabling controllers to focus
 * solely on HTTP request dispatching and response formatting, while delegating the
 * actual data retrieval and transformation logic to dedicated {@code GetHandler} implementations.</p>
 *
 * <p>The use of {@code GetHandler} enhances extensibility and testability, as the business logic
 * becomes a reusable, functional component that can be easily substituted and tested
 * independently of the HTTP context. It represents a concrete example of abstracting HTTP verbs
 * for more maintainable and adaptable web application architectures.</p>
 *
 * @param <Y> The type of the result produced by this handler, typically a data structure
 * intended for presentation (e.g., a DTO, a List of Maps, etc.).
 * @see org.nanotek.service.http.PresentationHandler
 * @see java.util.function.Supplier
 */
public interface GetHandler<Y> extends Supplier<Y>{
	// The get() method is inherited from Supplier<Y>
}
