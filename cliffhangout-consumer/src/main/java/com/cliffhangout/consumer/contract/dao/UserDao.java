package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;

public interface UserDao {
    void create(User user);
    void update(User user);
    void delete(User user);
    User find(int id);
    User findByLogin (String login);
}
