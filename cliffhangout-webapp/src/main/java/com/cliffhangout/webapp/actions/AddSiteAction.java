package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.webapp.AbstractAction;

import java.util.List;

public class AddSiteAction extends AbstractAction {
    private String title = "Ajouter un site";
    private String page = "/WEB-INF/subscriber/addModifySite.jsp";
    private String stylesheets = "/subscriber/addSite.css";
    private String jsPages = "/subscriber/addModifySite.js";
    private Site siteBean;
    private List<Sector> sectorsBeans;

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

    public Site getSiteBean() {
        return siteBean;
    }

    public void setSiteBean(Site siteBean) {
        this.siteBean = siteBean;
    }

    public String execute()
    {
        if(siteBean!=null)
        {
            return SUCCESS;
        }else{
            return INPUT;
        }
    }
}
