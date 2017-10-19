package com.cliffhangout.services;

import com.cliffhangout.beans.User;
import com.cliffhangout.dao.DaoException;
import com.cliffhangout.dao.DaoFactory;
import com.cliffhangout.dao.UserDao;

public class DeleteUser {
    private UserDao userDao;

    public DeleteUser()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

    public void purgeUser()
    {
        User user = new User();
        user.setId(6);
        user.setLogin("new");
        user.setPassword("nouveau");
        user.setSalt("test");
        user.setEmail("nouveau@gmail.com");
        user.setRole("test");
        try{
            userDao.delete(user);
        }catch(DaoException e){
            e.printStackTrace();
        }
    }
}
