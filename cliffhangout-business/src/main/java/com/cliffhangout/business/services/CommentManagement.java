package com.cliffhangout.business.services;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.consumer.contract.dao.CommentDao;
import com.cliffhangout.consumer.impl.dao.DaoException;
import com.cliffhangout.consumer.impl.dao.DaoFactory;

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
