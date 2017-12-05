package com.cliffhangout.business.impl;

import com.cliffhangout.business.contract.manager.*;

public class ManagerFactoryImpl implements ManagerFactory {
    private SiteManager siteManager;
    private UserManager userManager;
    private CommentManager commentManager;
    private DepartementRegionManager departementRegionManager;
    private TopoManager topoManager;
    private BorrowManager borrowManager;
    private WayManager wayManager;
    private SectorManager sectorManager;

    @Override
    public SiteManager getSiteManager() {
        return siteManager;
    }

    @Override
    public void setSiteManager(SiteManager pSiteManager) {
        siteManager = pSiteManager;
    }

    @Override
    public UserManager getUserManager() {
        return userManager;
    }

    @Override
    public void setUserManager(UserManager pUserManager) {
        userManager = pUserManager;
    }

    @Override
    public CommentManager getCommentManager() {
        return commentManager;
    }

    @Override
    public void setCommentManager(CommentManager commentManager) {
        this.commentManager = commentManager;
    }

    @Override
    public DepartementRegionManager getDepartementRegionManager() {
        return departementRegionManager;
    }

    @Override
    public void setDepartementRegionManager(DepartementRegionManager departementRegionManager) {
        this.departementRegionManager = departementRegionManager;
    }

    @Override
    public TopoManager getTopoManager() {
        return topoManager;
    }

    @Override
    public void setTopoManager(TopoManager topoManager) {
        this.topoManager = topoManager;
    }

    @Override
    public BorrowManager getBorrowManager() {
        return borrowManager;
    }

    @Override
    public void setBorrowManager(BorrowManager borrowManager) {
        this.borrowManager = borrowManager;
    }

    @Override
    public WayManager getWayManager() {
        return wayManager;
    }

    @Override
    public void setWayManager(WayManager wayManager) {
        this.wayManager = wayManager;
    }

    public SectorManager getSectorManager() {
        return sectorManager;
    }

    public void setSectorManager(SectorManager sectorManager) {
        this.sectorManager = sectorManager;
    }
}

