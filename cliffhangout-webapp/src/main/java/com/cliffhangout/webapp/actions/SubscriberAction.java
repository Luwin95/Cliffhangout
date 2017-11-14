package com.cliffhangout.webapp.actions;

import com.cliffhangout.webapp.AbstractAction;
import com.opensymphony.xwork2.ActionSupport;

public class SubscriberAction extends AbstractAction {
    private String title = "Subscriber Home";

    public String execute(){
        return SUCCESS;
    }
}
