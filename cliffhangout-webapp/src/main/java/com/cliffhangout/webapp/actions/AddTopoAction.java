package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.util.List;
import java.util.Map;

public class AddTopoAction extends AbstractAction implements SessionAware{

    private String page = "/WEB-INF/subscriber/addTopoForm.jsp";
    private String stylesheets = "signin.css";
    private String title = "Créer topos";
    private File upload;
    private String uploadContentType;
    private String uploadFileName;

    private Topo topoBean;
    Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
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

    public Topo getTopoBean() {
        return topoBean;
    }

    public void setTopoBean(Topo topoBean) {
        this.topoBean = topoBean;
    }

    @Override
    public String execute(){
        if(session.containsKey("sitesTopo") && session.get("sitesTopo") != null)
        {
            if(topoBean != null && upload != null)
            {
                getManagerFactory().getTopoManager().addTopo(topoBean, upload, uploadFileName, uploadContentType, session);
                return SUCCESS;
            }else{
                return INPUT;
            }
        }else{
            return "search";
        }
    }

    @Override
    public void validate() {
        if(topoBean != null)
        {
            if(topoBean.getName().equals(""))
            {
                addFieldError("topoBean.name", "Le nom du topo ne peut être vide");
            }
            if(!topoBean.getName().equals("") && topoBean.getName().length()<2)
            {
                addFieldError("topoBean.name", "Le nom du topo ne peut faire moins de deux caractères");
            }
            if(topoBean.getDescription().equals(""))
            {
                addFieldError("topoBean.description", "la description du topos ne peut être vide");
            }
        }
    }
}
