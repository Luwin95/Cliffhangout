package com.cliffhangout.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.services.GetSite;
import com.opensymphony.xwork2.ActionSupport;

public class SiteInfoAction extends ActionSupport {
    private String idSite;

    private String page = "/WEB-INF/site.jsp";

    private String stylesheets = "site.css";

    private String jsPages = "site.js";

    private Site site = new Site();

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String execute()
    {
        int id = Integer.parseInt(this.idSite);
        GetSite getSite = new GetSite();
        this.site = getSite.displaySite(id);
        return SUCCESS;
    }
}
