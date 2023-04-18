package io.ugurh.resttemplateinterceptor.annotations.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author harun ugur
 * @project rest-template-interceptor
 * @created 18.04.2023 - 19:21
 */

// Declaring an annotation for validating URLs
// @Retention is the time period
// Till what time you want your annotation to work around.
// @Target gives you that on what instance you can use this annotation
// Defined the constraint and the Validator Class!
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UrlValidatorImpl.class)
public @interface UrlValidatorAnnotation {

    // Error message which would be sent, You can set default value
    String message() default "Invalid Url Entered!";

    // Group of constraints
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
