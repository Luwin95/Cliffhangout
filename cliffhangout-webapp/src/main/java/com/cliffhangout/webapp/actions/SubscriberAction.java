package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
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
    private List<Topo> creatorTopos;
    private List<Borrow> borrows;
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

    public List<Topo> getCreatorTopos() {
        return creatorTopos;
    }

    public void setCreatorTopos(List<Topo> creatorTopos) {
        this.creatorTopos = creatorTopos;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute(){
        if(session.containsKey("sessionUser")) {
            setCreatorSites(getManagerFactory().getSiteManager().displayCreatorSites((User) session.get("sessionUser")));
            setCreatorTopos(getManagerFactory().getTopoManager().displayUserTopo((User) session.get("sessionUser")));
            setBorrows(getManagerFactory().getBorrowManager().displayBorrowByBorrower((User) session.get("sessionUser")));
            return SUCCESS;
        }else{
            return ERROR;
        }
    }
}
