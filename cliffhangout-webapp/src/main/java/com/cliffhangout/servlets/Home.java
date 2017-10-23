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
        /*UpdateSite updateSite = new UpdateSite();
        updateSite.editSite();
        DeleteSite deleteSite = new DeleteSite();
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
        List<Object> entities = getSite.displaySite();
        for(int i=0; i<entities.size(); i++){
            switch(i)
            {
                case 0:
                    request.setAttribute("site", entities.get(i));
                    break;
                case 1:
                    request.setAttribute("sectors", entities.get(i));
                    break;
                case 2:
                    request.setAttribute("ways", entities.get(i));
                    break;
                case 3:
                    request.setAttribute("lengths", entities.get(i));
                    break;
                case 4:
                    request.setAttribute("points", entities.get(i));
                    break;
            }
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }
}
