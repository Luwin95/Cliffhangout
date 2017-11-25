package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

public class TopoSearch extends AbstractAction implements SessionAware{
    private String title = "Chercher un topo";
    private String page = "/WEB-INF/subscriber/topoSearch.jsp";
    private String stylesheets = "/subscriber/topoSearch.css";
    private List<Topo>  topos;
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

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        setTopos(getManagerFactory().getTopoManager().displayTopoToBorrow((User) session.get("sessionUser")));
        return SUCCESS;
    }
}
