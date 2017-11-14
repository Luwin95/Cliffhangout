package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.business.contract.manager.CommentManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommentManagerImpl extends AbstractManagerImpl implements CommentManager{
    @Override
    public void addCommentSite(Comment comment, int idSite)
    {
        getDaoFactory().getCommentDao().createCommentSite(idSite, comment);
    }

    @Override
    public List<Comment> sortComments(List<Comment> comments)
    {
        Map<Integer, Comment> commentById = new HashMap<>();
        Iterator<Comment> iterator = comments.iterator();
        for(Comment comment : comments)
        {
            commentById.put(comment.getId(), comment);
        }
        while(iterator.hasNext())
        {
            Comment comment = iterator.next();
            if(comment.getParent() != null)
            {
                commentById.get(comment.getParent().getId()).addChild(comment);
                iterator.remove();
            }
        }
        return comments;
    }

    @Override
    public void getParentSiteComment(String parent, Comment commentBean)
    {
        if(parent!=null)
        {
            int parentId= Integer.parseInt(parent);
            Comment parentComment = getDaoFactory().getCommentDao().find(parentId);
            commentBean.setParent(parentComment);
        }
    }
}