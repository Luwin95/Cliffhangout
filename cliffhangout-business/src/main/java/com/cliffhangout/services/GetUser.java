package com.cliffhangout.services;

import com.cliffhangout.beans.User;
import com.cliffhangout.dao.DaoException;
import com.cliffhangout.dao.DaoFactory;
import com.cliffhangout.dao.UserDao;

public class GetUser {

    private UserDao userDao;

    public GetUser()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

    public User displayUser(int id) throws DaoException
    {
        User user = new User();
        try{
            user = userDao.find(id);

        }catch(DaoException e){
            e.printStackTrace();

        }
        return user;
    }

}
