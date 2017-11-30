package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.util.List;
import java.util.Map;

public class EditTopoAction extends AbstractAction implements SessionAware {
    private String idTopo;
    private Topo topoBean;
    private Topo topoToEdit;
    private String page = "/WEB-INF/subscriber/addTopoForm.jsp";
    private String stylesheets = "signin.css";
    private String title = "Modifier topo";
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    Map<String, Object> session;

    public String getIdTopo() {
        return idTopo;
    }

    public void setIdTopo(String idTopo) {
        this.idTopo = idTopo;
    }

    public Topo getTopoBean() {
        return topoBean;
    }

    public void setTopoBean(Topo topoBean) {
        this.topoBean = topoBean;
    }

    public Topo getTopoToEdit() {
        return topoToEdit;
    }

    public void setTopoToEdit(Topo topoToEdit) {
        this.topoToEdit = topoToEdit;
    }

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String getTitle() {
        return title;
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
        if(idTopo !=null)
        {
            if(session.containsKey("pageStatus"))
            {
                if(session.get("pageStatus").equals("add"))
                {
                    session.remove("sitesTopo");
                    session.remove("pageStatus");
                    session.put("pageStatus", "edit");
                }
            }else{
                session.put("pageStatus", "edit");
            }
            setTopoToEdit(getManagerFactory().getTopoManager().displayTopo(Integer.parseInt(idTopo)));
            if(!session.containsKey("sitesTopo"))
            {
                session.put("sitesTopo", topoToEdit.getSites());
            }
            if(topoToEdit!=null)
            {

                if(topoBean != null)
                {
                    if(upload != null)
                    {
                        getManagerFactory().getTopoManager().editTopo(topoBean, topoToEdit, upload, uploadFileName, uploadContentType, session);
                        return SUCCESS;
                    }else{
                        getManagerFactory().getTopoManager().editTopo(topoBean, topoToEdit, session);
                        return SUCCESS;
                    }
                }else{
                    return INPUT;
                }
            }else{
                return "home";
            }
        }else{
            return "home";
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
