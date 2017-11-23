package com.cliffhangout.webapp.actions;

import com.cliffhangout.webapp.AbstractAction;
import com.opensymphony.xwork2.ActionSupport;

public class RemoveSiteAction extends AbstractAction {
    private String idSite;

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
            int id = Integer.parseInt(this.idSite);
            getManagerFactory().getSiteManager().deleteSite(id);
            return SUCCESS;
        }else{
            return "home";
        }
    }
}
