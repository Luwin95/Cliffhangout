package com.cliffhangout.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.services.GetAllDepartmentsAndRegions;
import com.cliffhangout.services.GetSite;
import com.cliffhangout.services.SearchSite;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SearchSiteAction extends ActionSupport {
    private String page = "/WEB-INF/search.jsp";
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
    private String result="";
    private List<Site> sites = new ArrayList<Site>();
    private String title = "Rechercher un site";

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

    public String execute(){
        GetAllDepartmentsAndRegions getAllDepartmentsAndRegions = new GetAllDepartmentsAndRegions();
        List<Object> entities = getAllDepartmentsAndRegions.displayAllDepartmentsAndRegions();
        departements = entities.get(0);
        regions = entities.get(1);
        GetSite getSite = new GetSite();
        lastSites = getSite.displayLastTenSite();

        Hashtable criterias = new Hashtable();
        if(this.siteName != null || (this.addCriteria && (this.criteriaWays || this.criteriaQuotation || this.criteriaLocation)))
        {
            criterias.put("site-name",siteName);
            if(this.criteriaLocation)
            {
                criterias.put("criteria-location", this.criteriaLocation);
                if(this.criteriaLocationValue.equals("region"))
                {
                    criterias.put("criteria-region", this.criteriaRegion);
                }else if(this.criteriaLocationValue.equals("departement")){
                    criterias.put("criteria-departement", this.criteriaDepartement);
                }
            }
            if( this.criteriaQuotation)
            {
                criterias.put("criteria-cotation", this.criteriaQuotation);
                if(this.criteriaQuotationValue.equals("minimum"))
                {
                    criterias.put("criteria-cotation-min-value", this.criteriaQuotationMinValue);
                }else if(criteriaQuotationValue.equals("maximum")){
                    criterias.put("criteria-cotation-max-value", this.criteriaQuotationMaxValue);
                }

            }
            if(this.criteriaWays)
            {
                criterias.put("criteria-ways",  this.criteriaWays);
                criterias.put("criteria-way-number-min",this.criteriaWayNumberMin);
                criterias.put("criteria-way-number-max",this.criteriaWayNumberMax);
            }
            SearchSite searchSite = new SearchSite();
            this.setSites(searchSite.search(criterias));
            if(sites.isEmpty())
            {
                this.setResult("Aucun résultat n'a  été trouvé pour votre recherche");
            }else{
                this.setResult("Votre recherche a aboutie à "+sites.size()+" résultats");
            }
        }
        return SUCCESS;
    }
}