package com.cliffhangout.webapp.servlets.subscriber;

import com.cliffhangout.business.services.AddUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubscriberHome")
public class SubscriberHome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*AddUser addUser= new AddUser();
        addUser.CreateAccount();*/
        this.getServletContext().getRequestDispatcher("/WEB-INF/subscriber/home.jsp").forward(request,response);
    }
}
