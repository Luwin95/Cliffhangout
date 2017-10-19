package com.cliffhangout.servlets;

import com.cliffhangout.beans.User;
import com.cliffhangout.services.GetUser;
import com.cliffhangout.services.UpdateUser;

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
        UpdateUser updateUser = new UpdateUser();
        updateUser.editAccount();
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetUser getUser = new GetUser();
        User user = getUser.displayUser(7);
        request.setAttribute("user", user);
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }
}
