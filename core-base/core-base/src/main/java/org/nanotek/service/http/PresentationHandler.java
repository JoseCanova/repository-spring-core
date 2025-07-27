package org.nanotek.service.http;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody; // Import ResponseBody

/**
 * A specialization of {@link org.springframework.stereotype.Controller @Controller}
 * and {@link org.springframework.web.bind.annotation.ResponseBody @ResponseBody},
 * designed to explicitly mark classes that act as "Presentation Handlers" within
 * the application's domain.
 *
 * <p>In the context of Domain-Driven Design, types annotated with {@code @PresentationHandler}
 * are responsible for handling incoming requests (e.g., HTTP) and transforming
 * domain-specific data or command results into a format suitable for presentation
 * to a user interface or another consuming system (e.g., JSON, XML).
 * They bridge the gap between the application/domain layer and the external
 * communication mechanisms.</p>
 *
 * <p>Methods within a {@code @PresentationHandler} typically assume
 * {@link org.springframework.web.bind.annotation.ResponseBody @ResponseBody} semantics
 * by default, meaning their return values are directly bound to the web response body.
 * This aligns perfectly with the goal of serving presented data.</p>
 *
 * @author Your Name (or Nanotek Team)
 * @since 1.0.0 // Adjust as per your project's versioning
 * @see org.springframework.stereotype.Controller
 * @see org.springframework.web.bind.annotation.ResponseBody
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller // Meta-annotates with Spring's core Controller stereotype
@ResponseBody // Meta-annotates to assume @ResponseBody semantics for methods
public @interface PresentationHandler {

    /**
     * Alias for {@link Controller#value()}, allowing the component name to be suggested.
     * @return the suggested component name, if any (or empty String otherwise)
     */
    @AliasFor(annotation = Controller.class)
    String value() default "";

}