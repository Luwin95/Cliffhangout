package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class RemoveSiteAction extends AbstractAction implements SessionAware {
    private String idSite;

    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    @Override
    public String execute() throws Exception {
        if(!idSite.equals(""))
        {
            if(((User) session.get("sessionUser")).getRole().equals("ADMIN") || ((User) session.get("sessionUser")).getId() == getManagerFactory().getSiteManager().displaySite(Integer.parseInt(idSite)).getCreator().getId())
            {
                int id = Integer.parseInt(this.idSite);
                getManagerFactory().getSiteManager().deleteSite(id);
                return SUCCESS;
            }else{
                return "home";
            }
        }else{
            return "home";
        }
    }
}
