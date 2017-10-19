package com.cliffhangout.services;

import com.cliffhangout.beans.User;
import com.cliffhangout.dao.DaoException;
import com.cliffhangout.dao.DaoFactory;
import com.cliffhangout.dao.UserDao;

public class UpdateUser {
    private UserDao userDao;

    public UpdateUser()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

    public void editAccount()
    {
        User user = new User();
        user.setId(7);
        user.setLogin("bob");
        user.setPassword("nouveau");
        user.setSalt("lenouveau");
        user.setEmail("nouveau@gmail.com");
        user.setRole("test");
        try{
            userDao.update(user);
        }catch(DaoException e)
        {
            e.printStackTrace();
        }
    }
}
