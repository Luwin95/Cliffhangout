package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.commons.validator.routines.EmailValidator;

public class SigninAction extends AbstractAction {
    private String page = "/WEB-INF/signin.jsp";
    private String stylesheets = "signin.css";
    private String jsPages = "signin.js";
    private String title = "S'inscrire";
    private User userBean;
    private String passwordConfirmation;

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

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String paswordConfirmation) {
        this.passwordConfirmation = paswordConfirmation;
    }

    public String execute()
    {
        if(userBean!=null)
        {
            getManagerFactory().getUserManager().signinNewSubscriber(userBean);
            return SUCCESS;
        }else{
            return INPUT;
        }
    }

    @Override
    public void validate() {
        if(userBean!=null)
        {
            if(userBean.getLogin().equals("")) {
                addFieldError("userBean.login", "Le login est obligatoire");
            }
            if(!userBean.getLogin().equals("") && userBean.getLogin().length() <=2)
            {
                addFieldError("userBean.login", "Le login doit faire plus de deux caractères");
            }
            if(userBean.getPassword().equals(""))
            {
                addFieldError("userBean.password", "Le mot de passe ne peut être vide");
            }
            if(!userBean.getPassword().equals("") && userBean.getPassword().length()<5){
                addFieldError("userBean.password", "Le  mot de passe ne doit pas faire moins de cinq caractères");
            }
            if(!userBean.getPassword().equals(passwordConfirmation))
            {
                addFieldError("passwordConfirmation", "Les deux mots de passe sont différents");
            }
            if(!EmailValidator.getInstance().isValid(userBean.getEmail()))
            {
                addFieldError("userBean.email", "L'email entré n'est pas valide");
            }
        }
    }
}
