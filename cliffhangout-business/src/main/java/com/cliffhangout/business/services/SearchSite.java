package com.cliffhangout.business.services;

import com.cliffhangout.beans.Quotation;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.impl.dao.DaoException;
import com.cliffhangout.consumer.impl.dao.DaoFactory;
import com.cliffhangout.consumer.contract.dao.QuotationDao;
import com.cliffhangout.consumer.contract.dao.SiteDao;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class SearchSite {
    private SiteDao siteDao;
    private QuotationDao quotationDao;

    public SearchSite() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.siteDao = daoFactory.getSiteDao();
        this.quotationDao = daoFactory.getQuotationDao();
    }

    public List<Site> search(Hashtable criterias)
    {
        String sqlStatement = "SELECT * FROM site WHERE";
        List<String> sqlCriterias= new ArrayList<>();
        List<Site> sites = new ArrayList<Site>();
        Enumeration enumCriteria = criterias.keys();
        //Parcours des critères choisies par l'utilisateur
        while (enumCriteria.hasMoreElements()) {
            String key = (String) enumCriteria.nextElement();
            //Critère de sélection sur le nom du site
            if(criterias.containsKey("site-name") && key.equals("site-name") && !criterias.get(key).equals(""))
            {
                String siteName = (String) criterias.get(key);
                String siteNameCap = siteName.substring(0, 1).toUpperCase() + siteName.toLowerCase().substring(1);
                sqlCriterias.add(" (name LIKE '%"+siteName+"%' OR name LIKE '%"+siteName.toLowerCase()+"%' OR name LIKE '%"+siteName.toUpperCase()+"%' OR name LIKE '%"+siteNameCap+"%')") ;
            }

            //Critère de sélection sur la localisation
            if(criterias.containsKey("criteria-location") && key.equals("criteria-location") && criterias.get(key).equals(true))
            {
                //Critère de sélection sur une région française
                if(criterias.containsKey("criteria-region") && !criterias.get("criteria-region").equals("0"))
                {
                    sqlCriterias.add("( region_id = "+criterias.get("criteria-region")+")");
                }
                //critère de sélection sur un département français
                if(criterias.containsKey("criteria-departement") && !criterias.get("criteria-departement").equals("0"))
                {
                    sqlCriterias.add("( departement_code = '"+criterias.get("criteria-departement")+"')");
                }
            }
        }


        //Si aucun critère n'a été remplie, on récupère tous les sites
        if(sqlCriterias.isEmpty()){
            sqlStatement = "SELECT * FROM site";
        }else{
            //Sinon on ajoute tous les critères de sélection à la requête en les séparant par un AND
            for(String sqlCriteria : sqlCriterias)
            {
                System.out.println(sqlCriteria);
                sqlStatement = sqlStatement + sqlCriteria;
                if(!sqlCriteria.equals(sqlCriterias.get(sqlCriterias.size()-1)))
                {
                    sqlStatement += " AND ";
                }
            }
        }
        sqlStatement+= " ORDER BY id;";
        try{
            sites = siteDao.findAllBySearchCriteria(sqlStatement);
        }catch(DaoException e){
            e.printStackTrace();
        }

        //Critère de sélection sur la cotation maximale ou minimale du site
        if(criterias.containsKey("criteria-cotation") && criterias.get("criteria-cotation").equals(true))
        {
            //Critère de sélection sur la cotation minimale du site
            if(criterias.containsKey("criteria-cotation-min-value"))
            {
                String cotationMin = (String) criterias.get("criteria-cotation-min-value");
                try
                {
                    Quotation quotation = quotationDao.findByName(cotationMin);
                    int cpt = 0;
                    List<Site> sitesToRemove = new ArrayList<>();
                    for(Site site: sites)
                    {
                        for(Sector sector : site.getSectors())
                        {
                            for(Way way : sector.getWays())
                            {
                                if(way.getQuotation().getDifficulty() < quotation.getDifficulty())
                                {
                                    sitesToRemove.add(site);
                                }
                            }
                        }
                    }
                    sites.removeAll(sitesToRemove);
                }catch(DaoException e){
                    e.printStackTrace();
                }

            }
            //Critère de sélection sur la cotation maximale du site
            if(criterias.containsKey("criteria-cotation-max-value"))
            {
                String cotationMax = (String) criterias.get("criteria-cotation-max-value");
                try
                {
                    Quotation quotation = quotationDao.findByName(cotationMax);
                    List<Site> sitesToRemove = new ArrayList<>();
                    for(Site site: sites)
                    {
                        for(Sector sector : site.getSectors())
                        {
                            for(Way way : sector.getWays())
                            {
                                if(way.getQuotation().getDifficulty() > quotation.getDifficulty())
                                {
                                    sitesToRemove.add(site);
                                }
                            }
                        }
                    }
                    sites.removeAll(sitesToRemove);
                }catch(DaoException e){
                    e.printStackTrace();
                }
            }
        }
        if(criterias.containsKey("criteria-ways") && criterias.get("criteria-ways").equals(true))
        {
            int wayNbMin;
            int wayNbMax;

            List<Site> sitesToRemove = new ArrayList<>();

            if(criterias.get("criteria-way-number-min").equals(""))
            {
                wayNbMin =0;
            }else{
                wayNbMin = Integer.parseInt((String) criterias.get("criteria-way-number-min"));
            }

            if(criterias.get("criteria-way-number-max").equals(""))
            {
                wayNbMax =0;
            }else{

                wayNbMax = Integer.parseInt((String) criterias.get("criteria-way-number-max"));
            }

            if(wayNbMax < wayNbMin && wayNbMax>0) {
                sites = new ArrayList<>();
            }else if(wayNbMax==0 && wayNbMin>0)
            {
                for(Site site: sites)
                {
                    int nbWays = 0;
                    for(Sector sector : site.getSectors())
                    {
                        nbWays += sector.getWays().size();
                    }
                    if(nbWays<wayNbMin)
                    {
                        sitesToRemove.add(site);
                    }
                }
                sites.removeAll(sitesToRemove);
            }else{
                for(Site site: sites)
                {
                    int nbWays = 0;
                    for(Sector sector : site.getSectors())
                    {
                        nbWays += sector.getWays().size();
                    }
                    if(nbWays>wayNbMax ||nbWays<wayNbMin)
                    {
                        sitesToRemove.add(site);
                    }
                }
                sites.removeAll(sitesToRemove);
            }
        }
        return sites;
    }
}
