package com.cliffhangout.dao;

import com.cliffhangout.beans.Comment;

import java.sql.ResultSet;

public interface CommentDao {
    void create(Comment comment) throws DaoException;
    void update(Comment comment) throws DaoException;
    void delete(Comment comment) throws DaoException;
    Comment find(int id) throws DaoException;
    Comment buildComment(ResultSet resultSet) throws DaoException;
}
