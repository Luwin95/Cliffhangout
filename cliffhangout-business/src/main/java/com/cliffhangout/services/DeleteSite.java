package com.cliffhangout.services;

import com.cliffhangout.beans.*;
import com.cliffhangout.dao.*;

import java.util.List;
import java.util.Set;

public class DeleteSite {
    private SiteDao siteDao;
    private SectorDao sectorDao;
    private WayDao wayDao;
    private LengthDao lengthDao;
    private PointDao pointDao;

    public DeleteSite()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.siteDao = daoFactory.getSiteDao();
        this.sectorDao = daoFactory.getSectorDao();
        this.wayDao = daoFactory.getWayDao();
        this.lengthDao = daoFactory.getLengthDao();
        this.pointDao = daoFactory.getPointDao();
    }

    public void purgeSite()
    {
        try{
            Site site = siteDao.find(8);
            siteDao.delete(site);
        }catch(DaoException e){
            e.printStackTrace();
        }
    }
}
