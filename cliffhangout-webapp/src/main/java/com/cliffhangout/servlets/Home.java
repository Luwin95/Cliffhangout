package com.cliffhangout.servlets;

import com.cliffhangout.beans.User;
import com.cliffhangout.dao.DaoFactory;
import com.cliffhangout.dao.UserDao;
import com.cliffhangout.services.GetUser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/Home")
public class Home extends HttpServlet {


    public Home(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetUser getUser = new GetUser();
        User user = getUser.displayUser(1);
        request.setAttribute("user", user);
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }
}
