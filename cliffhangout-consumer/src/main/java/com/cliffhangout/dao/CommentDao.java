package com.cliffhangout.dao;

import com.cliffhangout.beans.Comment;

public interface CommentDao {
    void create(Comment comment) throws DaoException;
    void update(Comment comment) throws DaoException;
    void delete(Comment comment) throws DaoException;
}
