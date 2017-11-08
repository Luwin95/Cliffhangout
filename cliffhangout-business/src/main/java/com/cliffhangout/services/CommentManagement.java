package com.cliffhangout.services;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.dao.CommentDao;
import com.cliffhangout.dao.DaoException;
import com.cliffhangout.dao.DaoFactory;

public class CommentManagement {
    private CommentDao commentDao;

    public CommentManagement()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.commentDao = daoFactory.getCommentDao();
    }

    public void getParentSiteComment(String parent, Comment commentBean)
    {
        if(parent!=null)
        {
            int parentId= Integer.parseInt(parent);
            try
            {
                Comment parentComment = commentDao.find(parentId);
                commentBean.setParent(parentComment);
            }catch (DaoException e)
            {
                e.printStackTrace();
            }
        }
    }
}
