package com.cliffhangout.forms;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.User;
import com.cliffhangout.dao.CommentDao;
import com.cliffhangout.dao.DaoException;
import com.cliffhangout.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CommentForm {
    private static final String FIELD_CONTENT = "comment-content";
    private CommentDao commentDao;
    private Map<String, String> errors;
    public Map<String, String> getErrors() {
        return errors;
    }
    private void addError(String field, String message){
        errors.put(field, message);
    }

    public CommentForm()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.commentDao = daoFactory.getCommentDao();
    }

    public void addCommentSite(Comment comment, int idSite)
    {
        try{
            commentDao.createCommentSite(idSite, comment);
        }catch(DaoException e){
            e.printStackTrace();
        }
    }
}
