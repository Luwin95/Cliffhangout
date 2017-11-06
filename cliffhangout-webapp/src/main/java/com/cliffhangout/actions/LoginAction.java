package com.cliffhangout.actions;

import com.cliffhangout.beans.User;
import com.cliffhangout.forms.LoginForm;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class LoginAction extends ActionSupport implements RequestAware, SessionAware {
    private String username;
    private String password;
    private LoginForm loginForm=new LoginForm();
    private User user;
    protected Map session;
    protected Map request;
    private String page = "/WEB-INF/login.jsp";
    private String stylesheets = "login.css";
    private String jsPages = "login.js";
    private String title = "Login";

    public void setSession(Map session) {
        this.session = session;
    }

    public void setRequest(Map request) {
        this.request = request;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String getJsPages() {
        return jsPages;
    }


    public String getTitle() {
        return title;
    }
    public String execute()
    {
        if(this.user!= null)
        {
            session.put("sessionUser", getUser());
            return SUCCESS;
        }else{
            return ERROR;
        }
    }

    public void validate()
    {
        if( getUsername()== null) {
            addFieldError("username", "Le login est obligatoire");
        } else if( getUsername().length() <=2)
        {
            addFieldError("username", "Le login doit faire plus de deux caractères");
        }else if(!loginForm.isInDatabase(getUsername())){
            addFieldError("username", "Ce login est incorrect");
        }else{
            user = loginForm.getLoginUser(getUsername());
        }
        if(getPassword()== null)
        {
            addFieldError("password", "Le mot de passe ne peut être vide");
        }else  if(getPassword().length()<5){
            addFieldError("password", "Le  mot de passe ne doit pas faire moins de cinq caractères");
        }else if(!loginForm.validateCredentials(user,getPassword())){
            addFieldError("password", "Le mot de passe est incorrect");
        }
    }

}
