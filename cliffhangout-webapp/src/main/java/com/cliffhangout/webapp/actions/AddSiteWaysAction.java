package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

public class AddSiteWaysAction extends AbstractAction implements SessionAware {
    private String title = "Ajouter les voies d'un site";
    private String page = "/WEB-INF/subscriber/addModifySiteWays.jsp";
    private String stylesheets = "/subscriber/addSite.css";
    private String jsPages = "/subscriber/addModifySiteWays.js";
    private Site siteBean;
    List<Sector> sectors;
    private int cptSector;
    Map<String, Object> session;

    public Site getSiteBean() {
        return siteBean;
    }

    public void setSiteBean(Site siteBean) {
        this.siteBean = siteBean;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public int getCptSector() {
        return cptSector;
    }

    public void setCptSector(int cptSector) {
        this.cptSector = cptSector;
    }

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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        if(session.containsKey("sessionUser"))
        {
            if(session.containsKey("siteBean"))
            {
                setSiteBean((Site) session.get("siteBean"));
                if(siteBean!=null)
                {
                    setSectors(siteBean.getSectors());
                    for(Sector sector :sectors)
                    {
                        if(sector.getWays() !=null)
                        {
                            if(sector.getWays().size()>0)
                            {
                                cptSector++;
                            }
                        }
                    }
                    if(cptSector == sectors.size())
                    {
                        return INPUT;
                    }else{
                        return INPUT;
                    }
                }else{
                    return ERROR;
                }
            }else{
                return ERROR;
            }
        }else{
            return "login";
        }
    }
}
