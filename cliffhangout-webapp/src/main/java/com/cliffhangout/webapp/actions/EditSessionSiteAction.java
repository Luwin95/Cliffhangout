package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class EditSessionSiteAction extends AbstractAction implements SessionAware {
    private Map<String, Object> session;
    private Site siteToEdit;
    private Site siteBean;
    private String title = "Modifier un site";
    private String page = "/WEB-INF/subscriber/editSite.jsp";
    private String stylesheets = "/subscriber/addSite.css";
    private String idSite;

    public Site getSiteToEdit() {
        return siteToEdit;
    }

    public void setSiteToEdit(Site siteToEdit) {
        this.siteToEdit = siteToEdit;
    }

    public Site getSiteBean() {
        return siteBean;
    }

    public void setSiteBean(Site siteBean) {
        this.siteBean = siteBean;
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

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        if(session.containsKey("site"))
        {
            setSiteToEdit((Site) session.get("site"));
            if(siteBean !=null)
            {
                siteToEdit.setName(siteBean.getName());
                siteToEdit.setDescription(siteBean.getDescription());
                siteToEdit.setLocation(siteBean.getLocation());
                siteToEdit.setPostcode(siteBean.getPostcode());
                session.remove("site");
                session.put("site", siteToEdit);
                if(session.get("idSite") !=null)
                {
                    getManagerFactory().getSiteManager().updateSite(siteToEdit);
                    setIdSite((String)session.get("idSite"));
                    return "edit";
                }else{
                    return SUCCESS;
                }
            }else{
                return INPUT;
            }
        }else{
            if(session.get("idSite") !=null)
            {
                setIdSite((String)session.get("idSite"));
                return "edit";
            }else{
                return ERROR;
            }
        }
    }
}
