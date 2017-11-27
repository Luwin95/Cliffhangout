package com.cliffhangout.webapp.actions;

import com.cliffhangout.webapp.AbstractAction;

public class AdminAction extends AbstractAction {
    private String page = "/WEB-INF/admin/home.jsp";
    private String stylesheets = "/admin/home.css";
    private String title = "Accueil admin";

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
