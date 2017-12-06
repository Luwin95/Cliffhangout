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
    private boolean editSite;
    private boolean editSector;
    private boolean editWay;
    private boolean deleteSector;
    private boolean deleteWay;
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

    public boolean isEditSite() {
        return editSite;
    }

    public void setEditSite(boolean editSite) {
        this.editSite = editSite;
    }

    public boolean isDeleteSector() {
        return deleteSector;
    }

    public void setDeleteSector(boolean deleteSector) {
        this.deleteSector = deleteSector;
    }

    public boolean isDeleteWay() {
        return deleteWay;
    }

    public void setDeleteWay(boolean deleteWay) {
        this.deleteWay = deleteWay;
    }

    @Override
    public String execute(){
        String actionName= ActionContext.getContext().getName();

        if(session.containsKey("idSite"))
        {
            setIdSite((String) session.get("idSite"));
        }
        if(actionName.equals("editSite"))
        {
            if(session.containsKey("pageStatus"))
            {
                if(session.get("pageStatus").equals("addSite"))
                {
                    session.remove("site");
                    session.remove("pageStatus");
                    session.put("pageStatus", "editSite");
                }else{
                    session.put("pageStatus", "editSite");
                }
            }else{
                session.put("pageStatus", "editSite");
            }
            if(idSite != null)
            {
                if(((User) session.get("sessionUser")).getRole().equals("ADMIN") || ((User) session.get("sessionUser")).getId() == getManagerFactory().getSiteManager().displaySite(Integer.parseInt(idSite)).getCreator().getId())
                {
                    if(!session.containsKey("site"))
                    {
                        session.put("site",getManagerFactory().getSiteManager().displaySite(Integer.parseInt(idSite)));
                    }
                    session.put("idSite", idSite);
                }else{
                    return ERROR;
                }
            }else{
                return ERROR;
            }
        }else{
            if(session.containsKey("pageStatus"))
            {
                if(session.get("pageStatus").equals("editSite"))
                {
                    session.remove("site");
                    session.remove("pageStatus");
                    session.put("pageStatus", "addSite");
                }else{
                    session.put("pageStatus", "addSite");
                }
            }else{
                session.put("pageStatus", "addSite");
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
                return INPUT;
            }
        }else if(isAddSector())
        {
            return"addSector";
        }else if(isAddWay() && idSector!=null) {
            return "addWay";
        }else if(isEditSite()) {
            return "editSite";
        }else if(isEditSector()) {
            return "editSector";
        }else if(isEditWay()){
            return "editWay";
        }else if(isDeleteSector()) {
            if(actionName.equals("editSite"))
            {
                getManagerFactory().getSectorManager().deleteSector(((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)));
            }
            ((Site) session.get("site")).getSectors().remove(Integer.parseInt(idSector));
            return INPUT;
        }else if(isDeleteWay()){
            if(actionName.equals("editSite"))
            {
                getManagerFactory().getWayManager().deleteWay(((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).getWays().get(Integer.parseInt(idWay)));
            }
            ((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).getWays().remove(Integer.parseInt(idWay));
            return INPUT;
        }else if(isAddSite()){
            if(actionName.equals("editSite"))
            {
                session.remove("site");
                session.remove("idSite");
            }else{
                siteBean.setCreator((User) session.get("sessionUser"));
                getManagerFactory().getSiteManager().addSite(siteBean);
                session.remove("site");
            }
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
