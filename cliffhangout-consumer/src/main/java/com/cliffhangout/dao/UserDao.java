package com.cliffhangout.dao;

import com.cliffhangout.beans.User;

public interface UserDao {
    void create(User user) throws DaoException;
    void update(User user) throws DaoException;
    void delete(User user) throws DaoException;
    User find(int id) throws DaoException;

}
