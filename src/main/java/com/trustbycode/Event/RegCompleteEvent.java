package com.trustbycode.Event;

import com.trustbycode.Entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    public RegCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
