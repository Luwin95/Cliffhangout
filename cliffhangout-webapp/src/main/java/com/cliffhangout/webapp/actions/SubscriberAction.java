package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

public class SubscriberAction extends AbstractAction implements SessionAware {
    private String title = "Subscriber Home";
    private String page = "/WEB-INF/subscriber/home.jsp";
    private String stylesheets = "/subscriber/subscriber.css";
    private String jsPages = "/subscriber/signin.js";
    private List<Site> creatorSites;
    Map<String, Object> session;

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

    public List<Site> getCreatorSites() {
        return creatorSites;
    }

    public void setCreatorSites(List<Site> creatorSites) {
        this.creatorSites = creatorSites;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute(){
        if(session.containsKey("sessionUser")) {
            setCreatorSites(getManagerFactory().getSiteManager().displayCreatorSites((User) session.get("sessionUser")));
            return SUCCESS;
        }else{
            return ERROR;
        }
    }
}
