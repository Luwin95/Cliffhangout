package com.cliffhangout.business.contract.manager;

import com.cliffhangout.business.contract.manager.SiteManager;
import com.cliffhangout.business.contract.manager.UserManager;

public interface ManagerFactory {
    SiteManager getSiteManager();
    void setSiteManager(SiteManager pSiteManager);
    UserManager getUserManager();
    void setUserManager(UserManager pUserManager);
    CommentManager getCommentManager();
    void setCommentManager(CommentManager pCommentManager);
    DepartementRegionManager getDepartementRegionManager();
    void setDepartementRegionManager(DepartementRegionManager departementRegionManager);
}
