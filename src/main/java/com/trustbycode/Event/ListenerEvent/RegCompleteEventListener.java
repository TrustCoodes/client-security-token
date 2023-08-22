package com.trustbycode.Event.ListenerEvent;

import com.trustbycode.Entity.User;
import com.trustbycode.Event.RegCompleteEvent;
import com.trustbycode.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegCompleteEventListener
        implements ApplicationListener<RegCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegCompleteEvent event) {
        //Create Verification token for user and send back to login page then
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        // Send mail to user notifying successful registration
        String url = event.getApplicationUrl()
                + "verifyRegistration?token="
                + token;
        log.info("Click the link to verify your Registration: {}", url); //Verification mail sent
    }
}