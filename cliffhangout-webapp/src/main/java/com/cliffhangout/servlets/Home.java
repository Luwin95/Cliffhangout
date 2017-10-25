package com.cliffhangout.servlets;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.User;
import com.cliffhangout.dao.DaoException;
import com.cliffhangout.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/Home")
public class Home extends HttpServlet {


    public Home(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddUser addUser = new AddUser();
        addUser.CreateAccount();
        AddSite addSite = new AddSite();
        addSite.newSite();
        UpdateSite updateSite = new UpdateSite();
        updateSite.editSite();
        /*DeleteSite deleteSite = new DeleteSite();
        deleteSite.purgeSite();*/
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetUser getUser = new GetUser();
        User user = new User();
        try{
            user = getUser.displayUser(3);
        }catch(DaoException e){
            e.printStackTrace();
        }

        request.setAttribute("user", user);

        GetSite getSite = new GetSite();
        Site site = getSite.displaySite();
        request.setAttribute("site", site);
        request.setAttribute("page", "home.jsp");
        this.getServletContext().getRequestDispatcher("/WEB-INF/layout.jsp").forward(request,response);
    }
}
