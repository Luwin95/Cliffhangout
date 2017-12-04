package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Way;
import com.cliffhangout.webapp.AbstractAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

public class NewWayAction extends AbstractAction implements SessionAware{
    private Map<String, Object> session;
    private String title = "Ajouter une Voie";
    private String page = "/WEB-INF/subscriber/newWay.jsp";
    private String stylesheets = "/subscriber/addSite.css";
    private String jsPages = "/subscriber/newWay.js";
    private Way wayBean;
    private Way wayToEdit;
    private int lengthsNb;
    private double heightString;
    private List<String> pointsNb;
    private String idSector;
    private String idWay;

    public Way getWayBean() {
        return wayBean;
    }

    public void setWayBean(Way wayBean) {
        this.wayBean = wayBean;
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

    public int getLengthsNb() {
        return lengthsNb;
    }

    public void setLengthsNb(int lengthsNb) {
        this.lengthsNb = lengthsNb;
    }

    public double getHeightString() {
        return heightString;
    }

    public void setHeightString(double heightString) {
        this.heightString = heightString;
    }

    public List<String> getPointsNb() {
        return pointsNb;
    }

    public void setPointsNb(List<String> pointsNb) {
        this.pointsNb = pointsNb;
    }

    public String getIdSector() {
        return idSector;
    }

    public void setIdSector(String idSector) {
        this.idSector = idSector;
    }

    public String getIdWay() {
        return idWay;
    }

    public void setIdWay(String idWay) {
        this.idWay = idWay;
    }

    public Way getWayToEdit() {
        return wayToEdit;
    }

    public void setWayToEdit(Way wayToEdit) {
        this.wayToEdit = wayToEdit;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        String actionName= ActionContext.getContext().getName();
        if(actionName.equals("editWay"))
        {
            if(idWay == null)
            {
                return ERROR;
            }else{
                setWayToEdit(((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).getWays().get(Integer.parseInt(idWay)));
            }
        }
        if(idSector!=null)
        {
            if(wayBean!=null)
            {
                if(actionName.equals("editWay"))
                {
                    ((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).getWays().remove(Integer.parseInt(idWay));
                }
                getManagerFactory().getWayManager().configWay(wayBean, heightString, pointsNb, session, idSector);
                return SUCCESS;
            }else{
                return  INPUT;
            }
        }else{
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if(wayBean!=null)
        {
            if(wayBean.getName().equals(""))
            {
                addFieldError("wayBean.name", "Le nom d'une voie ne peut être nul");
            }
            if(!wayBean.getName().equals("") && wayBean.getName().length()<2)
            {
                addFieldError("wayBean.name", "Le nom d'une voie doit faire plus de deux caractères ");
            }
            if(heightString<=0)
            {
                addFieldError("wayBean.height", "La hauteur de la voie ne peut être négative ou nulle");
            }
            if(wayBean.getQuotation().getName().equals(""))
            {
                addFieldError("wayBean.quotation.name", "Une voie doit posséder une cotation");
            }
            /*if(wayBean.getHeightString().equals(""))
            {
                addFieldError("wayBean.heightString", "La hauteur d'une voie ne peut être nulle");
            }
            if(!wayBean.getHeightString().equals("") && !wayBean.getHeightString().matches("(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$"))
            {
                addFieldError("wayBean.heightString", "La hauteur entrée n'est pas au bon format");
            }*/
        }
    }
}
