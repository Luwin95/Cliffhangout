package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SigninAction extends AbstractAction {
    private String page = "/WEB-INF/signin.jsp";
    private String stylesheets = "signin.css";
    private String jsPages = "signin.js";
    private String title = "S'inscrire";
    private User userBean;
    private String passwordConfirmation;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;

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

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String execute()
    {
        if(userBean!=null)
        {
            if(upload !=null)
            {
                getManagerFactory().getUserManager().addProfileImage(upload, uploadContentType, uploadFileName, userBean);
            }
            return getManagerFactory().getUserManager().signinNewSubscriber(userBean);
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
