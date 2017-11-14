package com.cliffhangout.business.services;

import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.impl.dao.DaoException;
import com.cliffhangout.consumer.impl.dao.DaoFactory;
import com.cliffhangout.consumer.contract.dao.UserDao;

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
