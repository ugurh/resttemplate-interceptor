package io.ugurh.resttemplateinterceptor.annotations.api;

import io.ugurh.resttemplateinterceptor.annotations.dto.RequestDto;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harun ugur
 * @project rest-template-interceptor
 * @created 18.04.2023 - 19:37
 */
@RestController
@RequestMapping("/api/validator")
public class UrlValidatorController {

    @GetMapping("/url")
    @CacheEvict(value = "validators-url", key = "#dto.url")
    public String testAnnotation(@Valid @RequestBody RequestDto dto) {
        return "Testing url with annotation is success - " + dto.getUrl();
    }
}
