package com.cliffhangout.dao;

import com.cliffhangout.beans.User;

public interface UserDao {
    void create(User site);
    User find(int id);
}
