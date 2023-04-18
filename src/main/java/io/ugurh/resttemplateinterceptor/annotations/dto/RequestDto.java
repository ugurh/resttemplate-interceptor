package io.ugurh.resttemplateinterceptor.annotations.dto;

import io.ugurh.resttemplateinterceptor.annotations.annotations.UrlValidatorAnnotation;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 18.04.2023 - 19:38
 */

public class RequestDto {

    @UrlValidatorAnnotation()
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
