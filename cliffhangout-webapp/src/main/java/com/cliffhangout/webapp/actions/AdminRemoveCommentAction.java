package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.User;
import com.cliffhangout.webapp.AbstractAction;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class AdminRemoveCommentAction extends AbstractAction implements SessionAware {
    private String idComment;
    private Comment comment;
    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String execute() throws Exception {
        if(idComment!=null)
        {
            setComment(getManagerFactory().getCommentManager().getComment(Integer.parseInt(idComment)));
            if(comment!=null)
            {
                getManagerFactory().getCommentManager().deleteComment(comment);
                return SUCCESS;
            }else{
                return ERROR;
            }
        }else{
            return "home";
        }
    }
}
