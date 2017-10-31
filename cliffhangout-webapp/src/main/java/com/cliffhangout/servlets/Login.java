package com.cliffhangout.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("page", "login.jsp");
        request.setAttribute("stylesheets", "login.css");
        request.setAttribute("jsPages", "login.js");
        this.getServletContext().getRequestDispatcher("/WEB-INF/layout.jsp").forward(request,response);
    }
}
