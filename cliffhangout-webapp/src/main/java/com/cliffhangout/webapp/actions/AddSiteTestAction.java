package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class AddSiteTestAction extends AbstractAction implements SessionAware{
    private Map<String, Object> session;
    private Site siteBean;
    private int nbSector;
    private int[] nbWay;
    private int[][] nbLength;
    private int[][] nbPoint;
    private String title = "Ajouter un site";
    private String page = "/WEB-INF/subscriber/siteAddTest.jsp";
    private String stylesheets = "/subscriber/addSite.css";
    private String jsPages = "/subscriber/addSiteTest.js";

    public Site getSiteBean() {
        return siteBean;
    }

    public void setSiteBean(Site siteBean) {
        this.siteBean = siteBean;
    }

    public int getNbSector() {
        return nbSector;
    }

    public void setNbSector(int nbSector) {
        this.nbSector = nbSector;
    }

    public int[] getNbWay() {
        return nbWay;
    }

    public void setNbWay(int[] nbWay) {
        this.nbWay = nbWay;
    }

    public int[][] getNbLength() {
        return nbLength;
    }

    public void setNbLength(int[][] nbLength) {
        this.nbLength = nbLength;
    }

    public int[][] getNbPoint() {
        return nbPoint;
    }

    public void setNbPoint(int[][] nbPoint) {
        this.nbPoint = nbPoint;
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
        return SUCCESS;
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
