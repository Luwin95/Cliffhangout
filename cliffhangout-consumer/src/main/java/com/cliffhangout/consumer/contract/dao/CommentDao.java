package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface CommentDao {
    void create(Comment comment);
    void createCommentSite(int site_id, Comment comment) ;
    void createCommentTopo(int topo_id, Comment comment) ;
    void update(Comment comment);
    void delete(Comment comment);
    Comment find(int id);
    List<Comment> findAllByParent(Comment parent);
    List<Comment> findAllBySite(Site site);
}
