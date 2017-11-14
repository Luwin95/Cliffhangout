package com.cliffhangout.business.services;

import com.cliffhangout.beans.*;
import com.cliffhangout.consumer.contract.dao.SiteDao;
import com.cliffhangout.consumer.impl.dao.DaoException;
import com.cliffhangout.consumer.impl.dao.DaoFactory;

public class UpdateSite {
    private SiteDao siteDao;

    public UpdateSite()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.siteDao = daoFactory.getSiteDao();
    }

    public void editSite()
    {
        try{
            Site site = siteDao.find(10);
            site.setName("Site modifié");
            for(Sector sector:site.getSectors())
            {
                sector.setName("secteur modifié");
                for(Way way : sector.getWays()){
                    way.setName("voie modifié");
                    for(Length length : way.getLengths()){
                        length.setName("longueur modifiée");
                        for(Point point : length.getPoints())
                        {
                            point.setName("Point modifié");
                        }
                    }
                }
            }
            siteDao.update(site);
        }catch(DaoException e){
            e.printStackTrace();
        }
    }
}
