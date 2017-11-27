package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TopoSearch extends AbstractAction implements SessionAware{
    private String title = "Chercher un topo";
    private String page = "/WEB-INF/subscriber/topoSearch.jsp";
    private String stylesheets = "/subscriber/topoSearch.css";
    private List<Topo>  topos;
    private List<Borrow> borrows;
    private final File baseDownloadDir = new File("E:/P3/cliffhangout-webapp/src/main/webapp/resources/topos/");
    private String fileName;
    private InputStream inputStream;
    private long fileSize;
    private Date now;
    Map<String, Object> session;

    public String getTitle() {
        return title;
    }

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
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

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        setTopos(getManagerFactory().getTopoManager().displayTopoToBorrow((User) session.get("sessionUser")));
        setBorrows(getManagerFactory().getBorrowManager().displayBorrowByBorrower((User) session.get("sessionUser")));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        setNow(sdf.parse(sdf.format(new Date())));
        getManagerFactory().getBorrowManager().checkBorrowedTopos(borrows, topos, now);

        if(fileName!=null)
        {
            final File fileToDownload = new File(baseDownloadDir, fileName);
            fileSize = fileToDownload.length();
            inputStream = new FileInputStream(fileToDownload);
            return "downloadTopo";
        }else{
            return SUCCESS;
        }
    }
}
