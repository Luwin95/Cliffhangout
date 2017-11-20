package com.cliffhangout.webapp.actions;

import com.cliffhangout.webapp.AbstractAction;

public class SubscriberAction extends AbstractAction {
    private String title = "Subscriber Home";
    private String page = "/WEB-INF/subscriber/home.jsp";
    private String stylesheets = "/subscriber/subscriber.css";
    private String jsPages = "/subscriber/signin.js";

    public String getTitle() {
        return title;
    }

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String getJsPages() {
        return jsPages;
    }

    public String execute(){
        return SUCCESS;
    }
}
