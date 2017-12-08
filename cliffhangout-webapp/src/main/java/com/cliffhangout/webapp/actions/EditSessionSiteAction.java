package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Site;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.util.List;
import java.util.Map;

public class EditSessionSiteAction extends AbstractAction implements SessionAware {
    private Map<String, Object> session;
    private Site siteToEdit;
    private Site siteBean;
    private String title = "Modifier un site";
    private String page = "/WEB-INF/subscriber/editSite.jsp";
    private String stylesheets = "/subscriber/addSite.css";
    private String idSite;
    private int imageToDelete;
    private String imageSessionToDelete;
    private List<File> uploads;
    private List<String> uploadsContentType;
    private List<String> uploadsFileName;

    public Site getSiteToEdit() {
        return siteToEdit;
    }

    public void setSiteToEdit(Site siteToEdit) {
        this.siteToEdit = siteToEdit;
    }

    public Site getSiteBean() {
        return siteBean;
    }

    public void setSiteBean(Site siteBean) {
        this.siteBean = siteBean;
    }

    public String getTitle() {
        return title;
    }

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public int getImageToDelete() {
        return imageToDelete;
    }

    public void setImageToDelete(int imageToDelete) {
        this.imageToDelete = imageToDelete;
    }

    public List<File> getUploads() {
        return uploads;
    }

    public void setUploads(List<File> uploads) {
        this.uploads = uploads;
    }

    public List<String> getUploadsContentType() {
        return uploadsContentType;
    }

    public void setUploadsContentType(List<String> uploadsContentType) {
        this.uploadsContentType = uploadsContentType;
    }

    public List<String> getUploadsFileName() {
        return uploadsFileName;
    }

    public void setUploadsFileName(List<String> uploadsFileName) {
        this.uploadsFileName = uploadsFileName;
    }

    public String getImageSessionToDelete() {
        return imageSessionToDelete;
    }

    public void setImageSessionToDelete(String imageSessionToDelete) {
        this.imageSessionToDelete = imageSessionToDelete;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        if(session.containsKey("site"))
        {
            setSiteToEdit((Site) session.get("site"));
            if(siteBean !=null)
            {
                siteToEdit.setName(siteBean.getName());
                siteToEdit.setDescription(siteBean.getDescription());
                siteToEdit.setLocation(siteBean.getLocation());
                siteToEdit.setPostcode(siteBean.getPostcode());
                session.remove("site");
                session.put("site", siteToEdit);
                if(session.containsKey("pageStatus") && session.get("pageStatus").equals("addSite") && uploads!=null)
                {
                    if(session.containsKey("uploads"))
                    {
                        List<File> uploadListToEdit = (List<File>) session.get("uploads");
                        List<String> uploadListContentTypeToEdit = (List<String>) session.get("uploadsContentType");
                        List<String> uploadListFileNameToEdit = (List<String>) session.get("uploadsFileName");
                        uploadListToEdit.addAll(uploads);
                        uploadListContentTypeToEdit.addAll(uploadsContentType);
                        uploadListFileNameToEdit.addAll(uploadsFileName);
                        session.remove("uploads");
                        session.remove("uploadsContentType");
                        session.remove("uploadsFileName");
                        session.put("uploads", uploadListToEdit);
                        session.put("uploadsContentType", uploadListContentTypeToEdit);
                        session.put("uploadsFileName", uploadListFileNameToEdit);
                    }else{
                        session.put("uploads", uploads);
                        session.put("uploadsContentType", uploadsContentType);
                        session.put("uploadsFileName", uploadsFileName);
                    }
                }
                if(session.get("idSite") !=null)
                {
                    if(uploads!=null)
                    {
                        getManagerFactory().getSiteManager().updateSite(siteToEdit, uploads, uploadsContentType, uploadsFileName);
                    }else{
                        getManagerFactory().getSiteManager().updateSite(siteToEdit);
                    }
                    setIdSite((String)session.get("idSite"));
                    return "edit";
                }else{
                    return SUCCESS;
                }
            }
            else if(imageToDelete!=0){
                getManagerFactory().getSiteManager().deleteSiteImage(imageToDelete, session);
                return INPUT;
            }else if(imageSessionToDelete!=null){
                List<File> uploadListToEdit = (List<File>) session.get("uploads");
                List<String> uploadListContentTypeToEdit = (List<String>) session.get("uploadsContentType");
                List<String> uploadListFileNameToEdit = (List<String>) session.get("uploadsFileName");
                uploadListToEdit.remove(Integer.parseInt(imageSessionToDelete));
                uploadListContentTypeToEdit.remove(Integer.parseInt(imageSessionToDelete));
                uploadListFileNameToEdit.remove(Integer.parseInt(imageSessionToDelete));
                session.remove("uploads");
                session.remove("uploadsContentType");
                session.remove("uploadsFileName");
                session.put("uploads", uploadListToEdit);
                session.put("uploadsContentType", uploadListContentTypeToEdit);
                session.put("uploadsFileName", uploadListFileNameToEdit);
                return INPUT;
            }else{
                return INPUT;
            }
        }else{
            if(session.get("idSite") !=null)
            {
                setIdSite((String)session.get("idSite"));
                return "edit";
            }else{
                return ERROR;
            }
        }
    }
}
