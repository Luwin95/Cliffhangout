package com.cliffhangout.servlets;

import com.cliffhangout.beans.Region;
import com.cliffhangout.beans.Site;
import com.cliffhangout.services.GetAllDepartmentsAndRegions;
import com.cliffhangout.services.GetSite;
import com.cliffhangout.services.SearchSite;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

@WebServlet(name = "Search")
public class Search extends HttpServlet {
    public Search()
    {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hashtable criterias = new Hashtable();
        criterias.put("site-name",request.getParameter("site-name"));
        if(request.getParameterMap().containsKey("criteria-location") && request.getParameter("criteria-location").equals("on"))
        {
            criterias.put("criteria-location", request.getParameter("criteria-location"));
            if(request.getParameter("criteria-location-value").equals("region"))
            {
                criterias.put("criteria-region", request.getParameter("criteria-region"));
            }else if(request.getParameter("criteria-location-value").equals("departement")){
                criterias.put("criteria-departement", request.getParameter("criteria-departement"));
            }
        }
        if(request.getParameterMap().containsKey("criteria-cotation") && request.getParameter("criteria-cotation").equals("on"))
        {
            criterias.put("criteria-cotation", request.getParameter("criteria-cotation"));
            if(request.getParameter("criteria-cotation-value").equals("minimum"))
            {
                criterias.put("criteria-cotation-min-value", request.getParameter("criteria-cotation-min-value"));
            }else if(request.getParameter("criteria-cotation-value").equals("maximum")){
                criterias.put("criteria-cotation-max-value", request.getParameter("criteria-cotation-max-value"));
            }

        }
        if(request.getParameterMap().containsKey("criteria-ways") && request.getParameter("criteria-ways").equals("on"))
        {
            criterias.put("criteria-ways",  request.getParameter("criteria-ways"));
            criterias.put("criteria-way-number-min",request.getParameter("criteria-way-number-min"));
            criterias.put("criteria-way-number-max",request.getParameter("criteria-way-number-max"));
        }
        SearchSite searchSite = new SearchSite();
        List<Site> sites = searchSite.search(criterias);
        if(sites.isEmpty())
        {
            request.setAttribute("result", "Aucun résultat n'a  été trouvé pour votre recherche");
        }else{
            request.setAttribute("result", "Votre recherche a aboutie à "+sites.size()+" résultats");
        }
        request.setAttribute("sites", sites);
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetAllDepartmentsAndRegions getAllDepartmentsAndRegions = new GetAllDepartmentsAndRegions();
        List<Object> entities = getAllDepartmentsAndRegions.displayAllDepartmentsAndRegions();
        Object departements = entities.get(0);
        Object regions = entities.get(1);
        GetSite getSite = new GetSite();
        List<Site> lastSites = getSite.displayLastTenSite();
        request.setAttribute("lastSites", lastSites);
        request.setAttribute("departements", departements);
        request.setAttribute("regions", regions);
        request.setAttribute("page", "search.jsp");
        request.setAttribute("stylesheets", "search.css");
        request.setAttribute("jsPages", "search.js");
        this.getServletContext().getRequestDispatcher("/WEB-INF/layout.jsp").forward(request,response);
    }
}
