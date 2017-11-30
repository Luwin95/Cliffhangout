package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.User;
import com.cliffhangout.beans.Way;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddSiteAction extends AbstractAction implements SessionAware {
    private String title = "Ajouter un site";
    private String page = "/WEB-INF/subscriber/addModifySite.jsp";
    private String stylesheets = "/subscriber/addSite.css";
    private String jsPages = "/subscriber/addModifySite.js";
    private Site siteBean;
    private List<String> wayNb;
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

    public Site getSiteBean() {
        return siteBean;
    }

    public void setSiteBean(Site siteBean) {
        this.siteBean = siteBean;
    }

    public List<String> getWayNb() {
        return wayNb;
    }

    public void setWayNb(List<String> wayNb) {
        this.wayNb = wayNb;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute()
    {
        if(siteBean!=null)
        {
            if(session.containsKey("sessionUser"))
            {
                siteBean.setCreator((User) session.get("sessionUser"));
                for(String nb : wayNb)
                {
                    List<Way> ways= new ArrayList<>();
                    for(int i=0; i<Integer.parseInt(nb); i++)
                    {
                        Way wayToAdd = new Way();
                        ways.add(wayToAdd);
                    }
                    siteBean.getSectors().get(wayNb.indexOf(nb)).setWays(ways);
                }
                //getManagerFactory().getSiteManager().addSite(siteBean);
                session.put("siteBean", siteBean);
                return SUCCESS;
            }else{
                return ERROR;
            }
        }else{
            return INPUT;
        }
    }

    @Override
    public void validate() {
        if(siteBean!= null)
        {
            if(siteBean.getName().equals(""))
            {
                addFieldError("siteBean.name", "Le nom du site ne peut être vide");
            }
            if(!siteBean.getName().equals("") && siteBean.getName().length() <2)
            {
                addFieldError("siteBean.name", "Le nom du site doit faire au minimum deux caractères");
            }
            if(siteBean.getDescription().equals(""))
            {
                addFieldError("siteBean.description", "La description du site ne peut être vide");
            }
            if(siteBean.getLocation().equals(""))
            {
                addFieldError("siteBean.location", "Le nom de la commune entrée n'est pas valide");
            }
            if(!siteBean.getLocation().equals("") && siteBean.getLocation().length()<2)
            {
                addFieldError("siteBean.location", "Le nom de la commune entrée n'est pas valide");
            }
            if(siteBean.getPostcode().equals("") || siteBean.getPostcode().length()<5)
            {
                addFieldError("siteBean.postcode", "Le code postale entré n'est pas valide");
            }
if(siteBean.getSectors() != null)
            {
                for(int i = 1; i<siteBean.getSectors().size(); i++)
                {
                    if(siteBean.getSectors().get(i)!=null)
                    {
                        if(siteBean.getSectors().get(i).getName().equals(""))
                        {
                            addFieldError("siteBean.sectors["+i+"].name", "Le nom du secteur ne peut être vide");
                        }
                        if(!siteBean.getSectors().get(i).getName().equals("") && siteBean.getSectors().get(i).getName().length()<2 )
                        {
                            addFieldError("siteBean.sectors["+i+"].name", "Le nom du secteur ne doit pas faire moins de deux caractères");
                        }
                        if(siteBean.getSectors().get(i).getDescription().equals(""))
                        {
                            addFieldError("siteBean.sectors["+i+"].description", "La description du secteur ne peut être vide");
                        }
                    }
                }
            }
        }
    }
}
