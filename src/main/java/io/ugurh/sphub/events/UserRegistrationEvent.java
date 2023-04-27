package io.ugurh.sphub.events;

import org.springframework.context.ApplicationEvent;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 23.04.2023 - 21:19
 */
public class UserRegistrationEvent extends ApplicationEvent {
    private final String email;


    public UserRegistrationEvent(Object source, String email) {
        super(source);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
