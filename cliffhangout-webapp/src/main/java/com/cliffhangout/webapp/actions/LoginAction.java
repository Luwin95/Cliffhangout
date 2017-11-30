package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class LoginAction extends AbstractAction implements RequestAware, SessionAware {
    private String username;
    private String password;
    private User user;
    protected Map session;
    protected Map request;
    private String page = "/WEB-INF/login.jsp";
    private String stylesheets = "login.css";
    private String jsPages = "login.js";
    private String title = "Login";
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String execute()
    {
        if(!session.containsKey("sessionUser"))
        {
            if(username!=null)
            {
                user = getManagerFactory().getUserManager().getLoginUser(this.username);
                if(user!=null && getManagerFactory().getUserManager().validateCredentials(user,this.password))
                {
                    session.put("sessionUser", getUser());
                    return SUCCESS;
                }else{
                    setMessage("Informations de connexion incorrectes");
                    return ERROR;
                }
            }else{
                return INPUT;
            }
        }else{
            return "home";
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
    }
}
