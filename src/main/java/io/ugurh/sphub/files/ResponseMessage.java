package io.ugurh.sphub.files;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 5.05.2023 - 01:17
 */
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
