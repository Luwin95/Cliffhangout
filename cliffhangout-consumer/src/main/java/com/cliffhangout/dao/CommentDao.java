package com.cliffhangout.dao;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;

import java.sql.ResultSet;
import java.util.List;

public interface CommentDao {
    void create(Comment comment) throws DaoException;
    void update(Comment comment) throws DaoException;
    void delete(Comment comment) throws DaoException;
    Comment find(int id) throws DaoException;
    List<Comment> findAllByParent(Comment parent) throws DaoException;
    List<Comment> findAllBySite(Site site) throws DaoException;
    Comment buildComment(ResultSet resultSet) throws DaoException;
}
