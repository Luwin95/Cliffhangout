package com.cliffhangout.webapp.actions;

import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class AdminEditAccountActivationUserAction extends AbstractAction implements SessionAware {
    private Map<String, Object> session;
    private String idUser;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public String execute(){
        if(idUser!=null)
        {
            getManagerFactory().getUserManager().editAccountActivation(Integer.parseInt(idUser));
            return SUCCESS;
        }else{
            return "home";
        }
    }
}
