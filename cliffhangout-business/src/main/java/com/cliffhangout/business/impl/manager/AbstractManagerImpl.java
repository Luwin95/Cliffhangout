package com.cliffhangout.business.impl.manager;


import com.cliffhangout.consumer.contract.DaoFactory;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;
import javax.inject.Named;

public abstract class AbstractManagerImpl {
    private DaoFactory daoFactory;
    protected  DaoFactory getDaoFactory(){
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    private PlatformTransactionManager platformTransactionManager;
    protected PlatformTransactionManager getPlatformTransactionManager() {
        return platformTransactionManager;
    }
    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }
}
