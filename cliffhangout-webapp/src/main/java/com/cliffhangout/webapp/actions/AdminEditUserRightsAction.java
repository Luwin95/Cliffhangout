package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class AdminEditUserRightsAction extends AbstractAction implements SessionAware {
    private String idUser;
    private User user;
    private Map<String, Object> session;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() throws Exception {
        if(idUser!=null)
        {
            setUser(getManagerFactory().getUserManager().displayUser(Integer.parseInt(idUser)));
            if(user!=null)
            {
                getManagerFactory().getUserManager().editUserRights(user);
                return SUCCESS;
            }else{
                return ERROR;
            }
        }else{
            return "home";
        }
    }
}
