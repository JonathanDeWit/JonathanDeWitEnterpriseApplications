package com.example.jonathandewitenterpriseapplications.util;

import com.example.jonathandewitenterpriseapplications.models.UserDetail;
import org.springframework.context.ApplicationEvent;

public class OnCreteAccountEvent extends ApplicationEvent {
    private String appUrl;
    private UserDetail userDetail;
    public OnCreteAccountEvent(UserDetail userDetail) {
        super(userDetail);

        this.userDetail = userDetail;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
}
