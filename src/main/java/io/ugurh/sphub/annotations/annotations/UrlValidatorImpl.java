package io.ugurh.sphub.annotations.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author harun ugur
 * @project rest-template-interceptor
 * @created 18.04.2023 - 19:24
 */
public class UrlValidatorImpl implements ConstraintValidator<UrlValidatorAnnotation, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlValidatorImpl.class);

    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            LOGGER.error("Url validator got error from url : {} - message : {}", url, e.getLocalizedMessage());
            return false;
        }
    }
}
