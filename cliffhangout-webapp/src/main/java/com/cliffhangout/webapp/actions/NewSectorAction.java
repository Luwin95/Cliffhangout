package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.webapp.AbstractAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class NewSectorAction extends AbstractAction implements SessionAware{
    private Map<String, Object> session;
    private Sector sectorBean;
    private Sector sectorToEdit;
    private String title = "Ajouter un secteur";
    private String page = "/WEB-INF/subscriber/newSector.jsp";
    private String stylesheets = "/subscriber/addSite.css";
    private String idSector;

    public Sector getSectorBean() {
        return sectorBean;
    }

    public void setSectorBean(Sector sectorBean) {
        this.sectorBean = sectorBean;
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

    public String getIdSector() {
        return idSector;
    }

    public void setIdSector(String idSector) {
        this.idSector = idSector;
    }

    public Sector getSectorToEdit() {
        return sectorToEdit;
    }

    public void setSectorToEdit(Sector sectorToEdit) {
        this.sectorToEdit = sectorToEdit;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        String actionName= ActionContext.getContext().getName();
        if(actionName.equals("editSector"))
        {
            if(idSector == null)
            {
                return ERROR;
            }else{
                setSectorToEdit(((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)));
            }
        }
        if(session.containsKey("site"))
        {
            if(sectorBean!=null)
            {
                if(actionName.equals("editSector"))
                {
                    ((Site) session.get("site")).getSectors().remove(Integer.parseInt(idSector));
                }
                ((Site) session.get("site")).addSector(sectorBean);
                return SUCCESS;
            }else{
                return INPUT;
            }
        }else{
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if(sectorBean !=null)
        {
            if(sectorBean.getName().equals(""))
            {
                addFieldError("sectorBean.name", "Le nom du secteur ne peut être vide");
            }
            if(!sectorBean.getName().equals("") && sectorBean.getName().length()<2)
            {
                addFieldError("sectorBean.name", "Le nom du secteur ne peut être infèrieur à deux caractères");
            }
            if(sectorBean.getDescription().equals(""))
            {
                addFieldError("sectorBean.description", "La description du secteur ne peut être vide");
            }
        }
    }
}
