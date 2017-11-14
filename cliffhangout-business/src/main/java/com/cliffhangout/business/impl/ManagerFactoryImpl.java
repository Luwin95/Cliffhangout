package com.cliffhangout.business.impl;

import com.cliffhangout.business.contract.manager.SiteManager;
import com.cliffhangout.business.contract.manager.UserManager;
import com.cliffhangout.business.contract.manager.ManagerFactory;

public class ManagerFactoryImpl implements ManagerFactory {
    private SiteManager siteManager;
    private UserManager userManager;

    @Override
    public SiteManager getSiteManager() {
        return siteManager;
    }

    public void setSiteManager(SiteManager pSiteManager) {
        siteManager = pSiteManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager pUserManager) {
        userManager = pUserManager;
    }
}
