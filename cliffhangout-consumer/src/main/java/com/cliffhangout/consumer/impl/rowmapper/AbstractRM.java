package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.consumer.contract.DaoFactory;

public abstract class AbstractRM {
    private DaoFactory daoFactory;
    protected  DaoFactory getDaoFactory(){
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
