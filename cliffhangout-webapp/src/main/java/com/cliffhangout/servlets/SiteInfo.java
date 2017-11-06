package com.cliffhangout.servlets;

import com.cliffhangout.beans.Site;
import com.cliffhangout.forms.CommentForm;
import com.cliffhangout.services.GetSite;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SiteInfo")
public class SiteInfo extends HttpServlet {
    public SiteInfo()
    {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentForm commentForm = new CommentForm();
        //commentForm.addCommentSite(request);
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo().substring(1);
        int idSite = Integer.parseInt(pathInfo);
        GetSite getSite = new GetSite();
        Site site = getSite.displaySite(idSite);
        request.setAttribute("site", site);
        request.setAttribute("page", "site.jsp");
        request.setAttribute("stylesheets", "site.css");
        request.setAttribute("jsPages", "site.js");
        this.getServletContext().getRequestDispatcher("/WEB-INF/layout.jsp").forward(request,response);
    }
}
