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

    public void addCommentSite(HttpServletRequest request)
    {
        String content = getFieldValue(request, FIELD_CONTENT);
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute("sessionUser");
        String pathInfo = request.getPathInfo().substring(1);
        int idSite = Integer.parseInt(pathInfo);

        try{
            validateContent(content);
        }catch(Exception e){
            this.addError(FIELD_CONTENT, e.getMessage());
        }

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(author);
        try{
            commentDao.createCommentSite(idSite, comment);
        }catch(DaoException e){
            e.printStackTrace();
        }
    }

    public void validateContent(String content) throws Exception
    {
        if(content.isEmpty())
        {
            throw new Exception("Le contenu ne peut être vide");
        }
    }

    private static String getFieldValue(HttpServletRequest request, String fieldName)
    {
        String value = request.getParameter( fieldName );
        if( value == null || value.trim().length()==0)
        {
            return null;
        }else{
            return value;
        }
    }
}
