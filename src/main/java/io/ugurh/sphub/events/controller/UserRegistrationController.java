package io.ugurh.sphub.events.controller;

import io.ugurh.sphub.events.UserRegistrationEvent;
import io.ugurh.sphub.events.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 23.04.2023 - 21:27
 */
@RestController
@RequestMapping("/api/events/users")
public class UserRegistrationController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserRegistrationController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        applicationEventPublisher.publishEvent(new UserRegistrationEvent(this, user.getEmail()));
    }
}
