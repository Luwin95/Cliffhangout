package com.cliffhangout.business.services;

import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.impl.dao.DaoException;
import com.cliffhangout.consumer.impl.dao.DaoFactory;
import com.cliffhangout.consumer.contract.dao.UserDao;

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
