package com.cliffhangout.actions;

import com.cliffhangout.beans.User;
import com.cliffhangout.forms.LoginForm;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

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
        user = loginForm.getLoginUser(this.username);
        if(user.getId()!=0 && loginForm.validateCredentials(user,this.password))
        {
            session.put("sessionUser", getUser());
            return SUCCESS;
        }else{
            return ERROR;
        }
    }

    public void validate()
    {
        if(this.username!=null)
        {
            if(this.username.equals("")) {
                addFieldError("username", "Le login est obligatoire");
            }
            if(!this.username.equals("") && this.username.length() <=2)
            {
                addFieldError("username", "Le login doit faire plus de deux caractères");
            }
        }
        if(this.password!=null)
        {
            if(this.password.equals(""))
            {
                addFieldError("password", "Le mot de passe ne peut être vide");
            }
            if(!this.password.equals("") && this.password.length()<5){
                addFieldError("password", "Le  mot de passe ne doit pas faire moins de cinq caractères");
            }
        }
        if(this.password!= null && this.username!= null && this.password.length()>=5 && this.username.length()>2)
        {
            if(!loginForm.isInDatabase(this.username)){
                addFieldError("username", "Ce login est incorrect");
            }else if(!loginForm.validateCredentials(loginForm.getLoginUser(this.username),this.password))
            {
                addFieldError("password", "Le mot de passe est incorrect");
            }
        }
    }
}
