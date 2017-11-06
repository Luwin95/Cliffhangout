package com.cliffhangout.actions;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
    private String page = "home.jsp";
    private String stylesheets = "home.css";
    private String jsPages = "home.js";
    private String title = "Accueil";

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String getJsPages() {
        return jsPages;
    }

    public String getTitle() {
        return title;
    }
    public String execute(){

        return SUCCESS;
    }
}
