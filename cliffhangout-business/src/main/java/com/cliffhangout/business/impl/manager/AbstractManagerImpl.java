package com.cliffhangout.business.impl.manager;

import com.cliffhangout.consumer.impl.DaoFactoryImpl;

public abstract class AbstractManagerImpl {
    private DaoFactoryImpl daoFactory;
    protected  DaoFactoryImpl getDaoFactory(){
        return daoFactory;
    }
}
