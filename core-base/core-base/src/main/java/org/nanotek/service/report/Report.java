package org.nanotek.service.report;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Service;

/**
 * A custom stereotype annotation indicating that a class is a service primarily
 * responsible for generating reports or performing data aggregation and transformation.
 *
 * <p>Unlike {@link DatabaseReport} or other more specialized report stereotypes,
 * a service annotated with {@code @Report} is generic and not intrinsically
 * tied to a specific data source like a database or file system. Its role is to
 * aggregate or transform data (e.g., from other beans) to produce a report,
 * without necessarily performing direct I/O to a persistent store.</p>
 *
 * <p>This annotation is a specialization of Spring's {@link Service @Service} annotation
 * and will be detected by component scanning.</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service // Meta-annotated with @Service to be picked up by component scanning
public @interface Report {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";
}