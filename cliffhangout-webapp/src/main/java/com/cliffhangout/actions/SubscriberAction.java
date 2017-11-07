package com.cliffhangout.actions;

import com.opensymphony.xwork2.ActionSupport;

public class SubscriberAction extends ActionSupport {
    private String title = "Subscriber Home";

    public String execute(){
        return SUCCESS;
    }
}
