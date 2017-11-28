package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;

import java.util.List;

public class AdminAction extends AbstractAction {
    private String page = "/WEB-INF/admin/home.jsp";
    private String stylesheets = "/admin/home.css";
    private String title = "Accueil admin";
    private List<Site> sites;
    private List<Topo> topos;
    private List<User> users;
    private List<Comment> comments;

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String getTitle() {
        return title;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String execute() throws Exception {
        setSites(getManagerFactory().getSiteManager().displayAllSites());
        setTopos(getManagerFactory().getTopoManager().displayAllTopo());
        setUsers(getManagerFactory().getUserManager().displayAllUsers());
        setComments(getManagerFactory().getCommentManager().displayAllCommentsSignaled());
        return SUCCESS;
    }
}
