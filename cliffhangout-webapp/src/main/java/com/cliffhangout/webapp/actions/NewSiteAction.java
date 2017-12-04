package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class NewSiteAction extends AbstractAction implements SessionAware{
    private Map<String, Object> session;
    private Site siteBean;
    private boolean addSector;
    private boolean addWay;
    private boolean addSite;
    private boolean editSector;
    private boolean editWay;
    private  String idSite;
    private String idSector;
    private String idWay;
    private String title = "Ajouter un site";
    private String page = "/WEB-INF/subscriber/newSite.jsp";
    private String stylesheets = "/subscriber/addSite.css";

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Site getSiteBean() {
        return siteBean;
    }

    public void setSiteBean(Site siteBean) {
        this.siteBean = siteBean;
    }

    public boolean isAddSector() {
        return addSector;
    }

    public void setAddSector(boolean addSector) {
        this.addSector = addSector;
    }

    public boolean isAddWay() {
        return addWay;
    }

    public void setAddWay(boolean addWay) {
        this.addWay = addWay;
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

    public boolean isAddSite() {
        return addSite;
    }

    public void setAddSite(boolean addSite) {
        this.addSite = addSite;
    }

    public void setIdSector(String idSector) {
        this.idSector = idSector;
    }

    public boolean isEditSector() {
        return editSector;
    }

    public void setEditSector(boolean editSector) {
        this.editSector = editSector;
    }

    public boolean isEditWay() {
        return editWay;
    }

    public void setEditWay(boolean editWay) {
        this.editWay = editWay;
    }

    public String getIdWay() {
        return idWay;
    }

    public void setIdWay(String idWay) {
        this.idWay = idWay;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    @Override
    public String execute(){
        String actionName= ActionContext.getContext().getName();
        if(actionName.equals("editSite"))
        {
            if(idSite == null)
            {
                return ERROR;
            }else{
                setSiteBean(getManagerFactory().getSiteManager().displaySite(Integer.parseInt(idSite)));
            }
        }
        if(session.containsKey("site"))
        {
            setSiteBean((Site) session.get("site"));
        }
        if(!session.containsKey("site"))
        {
            if(siteBean!=null)
            {
                session.put("site", siteBean);
                return "display";
            }else{
                return ERROR;
            }
        }else if(isAddSector())
        {
            return"addSector";
        }else if(isAddWay() && idSector!=null) {
            return "addWay";
        }else if(isEditSector()) {
            return "editSector";
        }else if(isEditWay()){
            return "editWay";
        }else if(isAddSite()){
            siteBean.setCreator((User) session.get("sessionUser"));
            getManagerFactory().getSiteManager().addSite(siteBean);
            session.remove("site");
            return SUCCESS;
        }else{
            return INPUT;
        }
    }

    @Override
    public void validate() {
        if (siteBean != null) {
            if (siteBean.getName().equals("")) {
                addFieldError("siteBean.name", "Le nom du site ne peut être vide");
            }
            if (!siteBean.getName().equals("") && siteBean.getName().length() < 2) {
                addFieldError("siteBean.name", "Le nom du site doit faire au minimum deux caractères");
            }
            if (siteBean.getDescription().equals("")) {
                addFieldError("siteBean.description", "La description du site ne peut être vide");
            }
            if (siteBean.getLocation().equals("")) {
                addFieldError("siteBean.location", "Le nom de la commune entrée n'est pas valide");
            }
            if (!siteBean.getLocation().equals("") && siteBean.getLocation().length() < 2) {
                addFieldError("siteBean.location", "Le nom de la commune entrée n'est pas valide");
            }
            if (siteBean.getPostcode().equals("") || siteBean.getPostcode().length() < 5) {
                addFieldError("siteBean.postcode", "Le code postale entré n'est pas valide");
            }
        }
    }
}
