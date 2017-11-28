package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Comment;

import java.util.List;

public interface CommentManager {
    void addCommentSite(Comment comment, int idSite);
    List<Comment> sortComments(List<Comment> comments);
    void getParentSiteComment(String parent, Comment commentBean);
    List<Comment> displayAllCommentsSignaled();
    void reportComment(int idComment);
}
