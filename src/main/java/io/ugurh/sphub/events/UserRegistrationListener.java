package io.ugurh.sphub.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 23.04.2023 - 21:22
 */
public class UserRegistrationListener implements ApplicationListener<UserRegistrationEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationListener.class);

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        sendWelcomeEmail(event.getEmail());
    }

    private void sendWelcomeEmail(String email) {
        LOGGER.info("sendWelcomeEmail called with email: {} ", email);
    }
}
