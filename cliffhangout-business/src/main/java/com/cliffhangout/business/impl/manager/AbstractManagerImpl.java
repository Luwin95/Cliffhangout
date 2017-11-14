package com.cliffhangout.business.impl.manager;


import com.cliffhangout.consumer.contract.DaoFactory;

public abstract class AbstractManagerImpl {
    private DaoFactory daoFactory;
    protected  DaoFactory getDaoFactory(){
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
