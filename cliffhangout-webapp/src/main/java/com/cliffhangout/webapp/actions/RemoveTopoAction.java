package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class RemoveTopoAction extends AbstractAction implements SessionAware {
    private Map<String, Object> session;
    private String idTopo;

    public String getIdTopo() {
        return idTopo;
    }

    public void setIdTopo(String idTopo) {
        this.idTopo = idTopo;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute(){
        if(!idTopo.equals(""))
        {
            int id = Integer.parseInt(idTopo);
            getManagerFactory().getTopoManager().deleteTopo(id);
            if(((User) session.get("sessionUser")).getRole().equals("ADMIN"))
            {
                return "admin";
            }else{
                return SUCCESS;
            }
        }else{
            return "home";
        }
    }
}
