package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

public class SiteInfoAction extends AbstractAction implements SessionAware {
    private String idSite;

    private String page = "/WEB-INF/site.jsp";

    private String stylesheets = "site.css";

    private String[] jsPages = {"site.js", "comment.js"};

    private String title = "Infos Site";

    Map<String, Object> session;

    private Site site = new Site();

    private Comment commentBean= new Comment();

    private String parent;

    private String commentToReport;

    private List<Topo> topos;

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    public String[] getJsPages() {
        return jsPages;
    }

    public String getTitle() {
        return title;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Comment getCommentBean() {
        return commentBean;
    }

    public void setCommentBean(Comment commentBean) {
        this.commentBean = commentBean;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCommentToReport() {
        return commentToReport;
    }

    public void setCommentToReport(String commentToReport) {
        this.commentToReport = commentToReport;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    public String execute()
    {
        int id = Integer.parseInt(this.idSite);
        this.site = getManagerFactory().getSiteManager().displaySite(id);
        setTopos(getManagerFactory().getTopoManager().displayAllTopoBySite(site));
        if(site!= null) {
            if (commentBean.getContent() != null && session.containsKey("sessionUser")) {
                User author = (User) session.get("sessionUser");
                getManagerFactory().getCommentManager().getParentSiteComment(parent, commentBean);
                commentBean.setAuthor(author);
                getManagerFactory().getCommentManager().addCommentSite(commentBean, site.getId());
                return SUCCESS;
            }else if(commentToReport!=null){
                getManagerFactory().getCommentManager().reportComment(Integer.parseInt(commentToReport));
                return SUCCESS;
            }else{
                return INPUT;
            }
        }else{
            return "notFound";
        }
    }

    public void validate()
    {
        if(commentBean.getContent() != null){
            if(commentBean.getContent().equals(""))
            {
                addFieldError("commentBean.content", "Le commentaire ne peut être vide");
            }
        }
    }
}
