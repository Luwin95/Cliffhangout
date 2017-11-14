package com.cliffhangout.business.services;

import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.impl.dao.DaoException;
import com.cliffhangout.consumer.impl.dao.DaoFactory;
import com.cliffhangout.consumer.contract.dao.UserDao;

public class AddUser {
    private UserDao userDao;

    public AddUser()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

    public void CreateAccount()
    {
        User user = new User();
        Password password = new Password();
        user.setLogin("user2");
        String passwordPlain = "password";
        String passwordHash = password.hashPassword(passwordPlain);
        user.setPassword(passwordHash);
        user.setSalt("test");
        user.setEmail("nouveau@gmail.com");
        user.setRole("test");
        try{
            userDao.create(user);
        }catch(DaoException e)
        {
            e.printStackTrace();
        }
    }
}
