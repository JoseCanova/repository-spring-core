package org.nanotek.service.report;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A custom stereotype annotation indicating that a class is a service primarily
 * responsible for generating reports based on database-specific information.
 *
 * <p>This annotation specializes the broader {@link Report @Report} stereotype
 * to signify that the service's responsibilities include providing information
 * directly related to the database's structure, metadata, performance (e.g.,
 * capturing SQL queries, explain plans), or listings of data derived from the database,
 * as opposed to just aggregating "physical data" for individual entities.</p>
 *
 * <p>Examples of services that would use this annotation include those providing
 * database schema reports, transaction logs analysis, or query performance insights.</p>
 *
 * <p>This annotation is meta-annotated with {@link Report}, allowing for hierarchical
 * classification of reporting services.</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Report // Meta-annotated with @Report to inherit its semantics and component scanning
public @interface DatabaseReport {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";
}