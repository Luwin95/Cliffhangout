package com.cliffhangout.business.forms;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.consumer.contract.dao.CommentDao;
import com.cliffhangout.consumer.impl.dao.DaoException;
import com.cliffhangout.consumer.impl.dao.DaoFactory;

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
