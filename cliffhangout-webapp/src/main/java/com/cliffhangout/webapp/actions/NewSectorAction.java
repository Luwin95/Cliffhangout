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
    private String idSite;
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
        String actionName= ActionContext.getContext().getName();
        if(actionName.equals("editSector"))
        {
            if(idSector == null)
            {
                if(session.get("idSite") !=null)
                {
                    setIdSite((String)session.get("idSite"));
                    return "edit";
                }else{
                    return ERROR;
                }
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
                    sectorToEdit.setName(sectorBean.getName());
                    sectorToEdit.setDescription(sectorBean.getDescription());
                    ((Site) session.get("site")).getSectors().remove(Integer.parseInt(idSector));
                    ((Site) session.get("site")).addSector(sectorToEdit);
                }else{
                    ((Site) session.get("site")).addSector(sectorBean);
                }
                if(session.get("idSite") !=null)
                {
                    if(actionName.equals("editSector"))
                    {
                        getManagerFactory().getSectorManager().updateSector(sectorToEdit);
                    }else{
                        sectorBean.setSiteId(Integer.parseInt((String) session.get("idSite")));
                        getManagerFactory().getSectorManager().addSector(sectorBean);
                    }
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
