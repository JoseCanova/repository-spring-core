package org.nanotek.entities.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DateCompositionValidator.class)
@Documented
public @interface DateComposition {

    String message() default "{org.nanotek.beans.entities.validation" +
            "Instance is not a  Valid Date Composition.}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}