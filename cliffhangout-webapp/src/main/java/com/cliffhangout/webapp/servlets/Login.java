package com.cliffhangout.webapp.servlets;

import com.cliffhangout.beans.User;
import com.cliffhangout.business.forms.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginForm loginForm = new LoginForm();
        User user = loginForm.connectUser("","");
        if(loginForm.getErrors().isEmpty())
        {
            session.setAttribute("sessionUser", user);
            response.sendRedirect("/home");
        }else{
            session.setAttribute("sessionUser", null);
            request.setAttribute("loginForm", loginForm);
            request.setAttribute("user", user);
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("sessionUser")!=null)
        {
            response.sendRedirect("/home");
        }else{
            request.setAttribute("page", "login.jsp");
            request.setAttribute("stylesheets", "login.css");
            request.setAttribute("jsPages", "login.js");
            this.getServletContext().getRequestDispatcher("/WEB-INF/layout.jsp").forward(request,response);
        }
    }
}
