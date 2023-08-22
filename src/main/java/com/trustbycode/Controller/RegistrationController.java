package com.trustbycode.Controller;

import com.trustbycode.Entity.User;
import com.trustbycode.Event.RegCompleteEvent;
import com.trustbycode.Model.UserModel;
import com.trustbycode.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    Logger logger = LoggerFactory
            .getLogger(RegistrationController.class);

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegCompleteEvent(user, applicationUrl(request)));
        logger.info("Registration Activated");
        return "Success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    }

}
