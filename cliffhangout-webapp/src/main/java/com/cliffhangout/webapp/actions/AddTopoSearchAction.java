package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AddTopoSearchAction extends AbstractAction implements SessionAware{
    private String page = "/WEB-INF/subscriber/addTopoSearch.jsp";
    private String stylesheets = "search.css";
    private String jsPages = "search.js";
    private List<Site> lastSites;
    private Object departements;
    private Object regions;
    private String siteName;
    private boolean addCriteria;
    private boolean criteriaLocation;
    private boolean criteriaQuotation;
    private boolean criteriaWays;
    private String criteriaLocationValue ="";
    private String criteriaQuotationValue = "";
    private String criteriaRegion = "";
    private String criteriaDepartement ="";
    private String criteriaQuotationMinValue= "";
    private String criteriaQuotationMaxValue="";
    private String criteriaWayNumberMin ="";
    private String criteriaWayNumberMax ="";
    private String result;
    private List<Site> sites = new ArrayList<Site>();
    private String title = "Sélectionner les sites du topos";
    private List<String> siteToAdd;
    private List<String> sitesToRemove;
    private List<Site> sitesChosen;
    Map<String, Object> session;

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String getJsPages() {
        return jsPages;
    }

    public List<Site> getLastSites() {
        return lastSites;
    }

    public Object getDepartements() {
        return departements;
    }

    public Object getRegions() {
        return regions;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public boolean isAddCriteria() {
        return addCriteria;
    }

    public void setAddCriteria(boolean addCriteria) {
        this.addCriteria = addCriteria;
    }

    public boolean isCriteriaLocation() {
        return criteriaLocation;
    }

    public void setCriteriaLocation(boolean criteriaLocation) {
        this.criteriaLocation = criteriaLocation;
    }

    public boolean isCriteriaQuotation() {
        return criteriaQuotation;
    }

    public void setCriteriaQuotation(boolean criteriaQuotation) {
        this.criteriaQuotation = criteriaQuotation;
    }

    public boolean isCriteriaWays() {
        return criteriaWays;
    }

    public void setCriteriaWays(boolean criteriaWays) {
        this.criteriaWays = criteriaWays;
    }

    public String getCriteriaLocationValue() {
        return criteriaLocationValue;
    }

    public void setCriteriaLocationValue(String criteriaLocationValue) {
        this.criteriaLocationValue = criteriaLocationValue;
    }

    public String getCriteriaQuotationValue() {
        return criteriaQuotationValue;
    }

    public void setCriteriaQuotationValue(String criteriaQuotationValue) {
        this.criteriaQuotationValue = criteriaQuotationValue;
    }

    public String getCriteriaRegion() {
        return criteriaRegion;
    }

    public void setCriteriaRegion(String criteriaRegion) {
        this.criteriaRegion = criteriaRegion;
    }

    public String getCriteriaDepartement() {
        return criteriaDepartement;
    }

    public void setCriteriaDepartement(String criteriaDepartement) {
        this.criteriaDepartement = criteriaDepartement;
    }

    public String getCriteriaQuotationMinValue() {
        return criteriaQuotationMinValue;
    }

    public void setCriteriaQuotationMinValue(String criteriaQuotationMinValue) {
        this.criteriaQuotationMinValue = criteriaQuotationMinValue;
    }

    public String getCriteriaQuotationMaxValue() {
        return criteriaQuotationMaxValue;
    }

    public void setCriteriaQuotationMaxValue(String criteriaQuotationMaxValue) {
        this.criteriaQuotationMaxValue = criteriaQuotationMaxValue;
    }

    public String getCriteriaWayNumberMin() {
        return criteriaWayNumberMin;
    }

    public void setCriteriaWayNumberMin(String criteriaWayNumberMin) {
        this.criteriaWayNumberMin = criteriaWayNumberMin;
    }

    public String getCriteriaWayNumberMax() {
        return criteriaWayNumberMax;
    }

    public void setCriteriaWayNumberMax(String criteriaWayNumberMax) {
        this.criteriaWayNumberMax = criteriaWayNumberMax;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getSiteToAdd() {
        return siteToAdd;
    }

    public void setSiteToAdd(List<String> siteToAdd) {
        this.siteToAdd = siteToAdd;
    }

    public List<String> getSitesToRemove() {
        return sitesToRemove;
    }

    public void setSitesToRemove(List<String> sitesToRemove) {
        this.sitesToRemove = sitesToRemove;
    }

    public List<Site> getSitesChosen() {
        return sitesChosen;
    }

    public void setSitesChosen(List<Site> sitesChosen) {
        this.sitesChosen = sitesChosen;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute(){
        if(session.containsKey("sitesTopo") && session.get("sitesTopo")!=null)
        {
            setSitesChosen((List<Site>) session.get("sitesTopo"));
        }
        if(this.siteName != null || (this.addCriteria && (this.criteriaWays || this.criteriaQuotation || this.criteriaLocation))) {
            Hashtable criterias = new Hashtable();
            criterias.put("site-name", siteName);
            if (this.criteriaLocation) {
                criterias.put("criteria-location", this.criteriaLocation);
                if (this.criteriaLocationValue.equals("region")) {
                    criterias.put("criteria-region", this.criteriaRegion);
                } else if (this.criteriaLocationValue.equals("departement")) {
                    criterias.put("criteria-departement", this.criteriaDepartement);
                }
            }
            if (this.criteriaQuotation) {
                criterias.put("criteria-cotation", this.criteriaQuotation);
                if (this.criteriaQuotationValue.equals("minimum")) {
                    criterias.put("criteria-cotation-min-value", this.criteriaQuotationMinValue);
                } else if (criteriaQuotationValue.equals("maximum")) {
                    criterias.put("criteria-cotation-max-value", this.criteriaQuotationMaxValue);
                }

            }
            if (this.criteriaWays) {
                criterias.put("criteria-ways", this.criteriaWays);
                criterias.put("criteria-way-number-min", this.criteriaWayNumberMin);
                criterias.put("criteria-way-number-max", this.criteriaWayNumberMax);
            }
            this.setSites(getManagerFactory().getSiteManager().search(criterias));
            if (sites.isEmpty()) {
                this.setResult("Aucun résultat n'a  été trouvé pour votre recherche");
                return ERROR;
            } else {
                this.setResult("Votre recherche a aboutie à " + sites.size() + " résultats");
                return SUCCESS;
            }
        }else if(siteToAdd != null) {
            List<Site> tempSites = getManagerFactory().getSiteManager().displaySitesChosen(siteToAdd);
            if (tempSites != null) {
                if (session.containsKey("sitesTopo")) {
                    List<Site> sitesTopo = (List<Site>) session.get("sitesTopo");
                    getManagerFactory().getSiteManager().isInSession(sitesTopo, tempSites);
                    session.remove("sitesTopo");
                    sitesTopo.addAll(tempSites);
                    session.put("sitesTopo", sitesTopo);
                } else {
                    session.put("sitesTopo", tempSites);
                }
            }
            return "search";
        }else if (sitesToRemove != null)
        {
            getManagerFactory().getSiteManager().removeSitesChosen(sitesToRemove, session);
            return "search";
        }else{
            List<Object> entities = getManagerFactory().getDepartementRegionManager().displayAllDepartmentsAndRegions();
            departements = entities.get(0);
            regions = entities.get(1);
            return INPUT;
        }
    }
}
