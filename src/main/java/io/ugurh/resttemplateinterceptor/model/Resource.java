package io.ugurh.resttemplateinterceptor.model;

/**
 * @author harun ugur
 * @project rest-template-interceptor
 * @created 13.04.2023 - 22:09
 */
public class Resource {

    private String message;

    public Resource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
