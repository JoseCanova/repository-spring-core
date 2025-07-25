package org.nanotek;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A meta-annotation indicating that an interface (or class) serves primarily
 * as a "tag" or "marker" within the application's type system.
 *
 * <p>Interfaces annotated with {@code @TagInterface} typically introduce no
 * new abstract methods but extend existing functional interfaces (like {@link java.util.function.Function})
 * or serve as purely empty interfaces. Their primary purpose is to provide
 * enhanced semantic meaning and improve code readability within a specific
 * domain context, effectively acting as a domain-specific alias for a more
 * generic type.</p>
 *
 * <p>This annotation facilitates a clear expression of intent, allowing developers
 * to reason about types based on their role and conceptual category, rather
 * than solely on their structural definition. It enables frameworks or other
 * parts of the application to identify and process these tagged types distinctly
 * through reflection or compile-time checks, without requiring explicit method
 * implementations on the part of the tagged interface.</p>
 *
 * <h3>Conceptual Background:</h3>
 * <p>This pattern is akin to the well-known "Marker Interface Pattern" (or "Tagging Interface")
 * in Java, famously exemplified by {@code java.io.Serializable} and {@code java.lang.Cloneable}.
 * As described by Joshua Bloch in "Effective Java" (Item 37), marker interfaces
 * define types that can be checked at compile-time using the {@code instanceof} operator,
 * enabling robust type-safe operations. While modern Java also offers annotations
 * for metadata, tag interfaces remain valuable for defining type hierarchies and
 * leveraging Java's strong type system for conceptual clarity.</p>
 *
 * @see java.io.Serializable
 * @see java.lang.Cloneable
 * @see java.util.function.Function
 * @see <a href="https://en.wikipedia.org/wiki/Marker_interface_pattern">Marker interface pattern on Wikipedia</a>
 * @see <a href="https://refactoring.guru/design-patterns/marker-interface">Marker Interface Pattern on Refactoring.Guru</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TagInterface {
}