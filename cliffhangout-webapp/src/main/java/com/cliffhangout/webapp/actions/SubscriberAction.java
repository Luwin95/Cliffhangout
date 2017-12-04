package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SubscriberAction extends AbstractAction implements SessionAware {
    private String title = "Subscriber Home";
    private String page = "/WEB-INF/subscriber/home.jsp";
    private String stylesheets = "/subscriber/subscriber.css";
    private String jsPages = "/subscriber/signin.js";
    private List<Site> creatorSites;
    private List<Topo> creatorTopos;
    private List<Borrow> borrows;
    Map<String, Object> session;
    private Date now;
    private String fileName;
    private final File baseDownloadDir = new File("E:/UploadCliffhangout/topos/");
    private InputStream inputStream;
    private long fileSize;

    public String getTitle() {
        return title;
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

    public List<Site> getCreatorSites() {
        return creatorSites;
    }

    public void setCreatorSites(List<Site> creatorSites) {
        this.creatorSites = creatorSites;
    }

    public List<Topo> getCreatorTopos() {
        return creatorTopos;
    }

    public void setCreatorTopos(List<Topo> creatorTopos) {
        this.creatorTopos = creatorTopos;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public InputStream getFileToDownload() {
        return inputStream;
    }

    public String getContentDisposition() {
        return "attachment;filename=\"" + fileName + "\"";
    }

    public String getContentType() {
        return "application/pdf";
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute(){
        if(session.containsKey("sessionUser")) {
            if(fileName!=null)
            {
                try{
                    final File fileToDownload = new File(baseDownloadDir, fileName);
                    fileSize = fileToDownload.length();
                    inputStream = new FileInputStream(fileToDownload);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                return "downloadTopo";
            }else{
                try{
                    setCreatorSites(getManagerFactory().getSiteManager().displayCreatorSites((User) session.get("sessionUser")));
                    setCreatorTopos(getManagerFactory().getTopoManager().displayUserTopo((User) session.get("sessionUser")));
                    setBorrows(getManagerFactory().getBorrowManager().displayBorrowByBorrower((User) session.get("sessionUser")));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    setNow(sdf.parse(sdf.format(new Date())));
                }catch(ParseException e)
                {
                    e.printStackTrace();
                }
                return SUCCESS;
            }

        }else{
            return ERROR;
        }
    }
}
