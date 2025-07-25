package org.nanotek.service.report; // Assuming this package for report-related services/stereotypes

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Service; // Meta-annotate with @Service as it's a service-like component

/**
 * A custom stereotype annotation indicating that a class is a "Presenter" service
 * responsible for transforming raw data or aggregated beans into various consumable
 * output formats.
 *
 * <p>Services annotated with {@code @ReportPresenter} act as a bridge between
 * core data-generating services (like those providing raw reports) and the final
 * presentation layer or external consumers. Their main responsibility is to apply
 * a given transformation to input data, which can result in:</p>
 * <ul>
 * <li>An "enriched" bean (e.g., adding headers, footers, or metadata).</li>
 * <li>A plain text format (e.g., CSV, formatted strings).</li>
 * <li>A binary report format (e.g., PDF, Jasper report output).</li>
 * <li>Any other client-specific presentation format.</li>
 * </ul>
 *
 * <p>This stereotype helps in clearly separating the concerns of data retrieval and
 * processing from data formatting and presentation. It is conceptually similar
 * to how {@code @RestController} handles HTTP-specific presentation, but
 * {@code @ReportPresenter} focuses on format transformation regardless of the
 * communication protocol.</p>
 *
 * <p>This annotation is a specialization of Spring's {@link Service @Service} annotation
 * and will be detected by component scanning.</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service // This ensures Spring recognizes it as a component
public @interface ReportPresenter {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";
}