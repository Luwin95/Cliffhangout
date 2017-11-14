package com.cliffhangout.webapp.actions;

import com.cliffhangout.webapp.AbstractAction;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class LogoutAction extends AbstractAction implements SessionAware {
    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute(){
        session.clear();
        return SUCCESS;
    }
}
