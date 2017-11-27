package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.util.Map;

public class EditProfileAction extends AbstractAction implements SessionAware{
    private String page = "/WEB-INF/subscriber/editProfile.jsp";
    private String stylesheets = "signin.css";
    private String jsPages = "signin.js";
    private String title = "Mon profil";
    private Map<String, Object> session;
    private User userBean;
    private User userSession;
    private String passwordConfirmation;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;


    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public User getUserSession() {
        return userSession;
    }

    public void setUserSession(User userSession) {
        this.userSession = userSession;
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        if(session.containsKey("sessionUser"))
        {
            setUserSession((User) session.get("sessionUser"));
            if(userBean!=null)
            {
                if(upload!=null)
                {
                    getManagerFactory().getUserManager().editProfile(userBean, userSession, upload, uploadContentType, uploadFileName);
                    return SUCCESS;
                }else{
                    getManagerFactory().getUserManager().editProfile(userBean, userSession);
                    return SUCCESS;
                }
            }else{
                return INPUT;
            }
        }else{
            return "notFound";
        }
    }

    @Override
    public void validate() {
        if(userBean !=null)
        {
            if(!userBean.getPassword().equals("") || !passwordConfirmation.equals("") )
            {
                if(!userBean.getPassword().equals(passwordConfirmation))
                {
                    addFieldError("passwordConfirmation", "Les deux mots de passe sont différents");
                }
                if(userBean.getPassword().equals(""))
                {
                    addFieldError("userBean.password", "Le mot de passe ne peut être vide");
                }
                if(!userBean.getPassword().equals("") && userBean.getPassword().length()<5){
                    addFieldError("userBean.password", "Le  mot de passe ne doit pas faire moins de cinq caractères");
                }
            }
            if(userBean.getLogin().equals("")) {
                addFieldError("userBean.login", "Le login est obligatoire");
            }
            if(!userBean.getLogin().equals("") && userBean.getLogin().length() <=2)
            {
                addFieldError("userBean.login", "Le login doit faire plus de deux caractères");
            }
            if(!EmailValidator.getInstance().isValid(userBean.getEmail()))
            {
                addFieldError("userBean.email", "L'email entré n'est pas valide");
            }
        }
    }
}
