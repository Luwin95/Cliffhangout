package com.cliffhangout.services;

import com.cliffhangout.beans.*;
import com.cliffhangout.dao.*;

import java.util.HashSet;
import java.util.Set;

public class UpdateSite {
    private SiteDao siteDao;
    private SectorDao sectorDao;
    private WayDao wayDao;
    private LengthDao lengthDao;
    private PointDao pointDao;

    public UpdateSite()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.siteDao = daoFactory.getSiteDao();
        this.sectorDao = daoFactory.getSectorDao();
        this.wayDao = daoFactory.getWayDao();
        this.lengthDao = daoFactory.getLengthDao();
        this.pointDao = daoFactory.getPointDao();
    }

    public void editSite()
    {
        try{
            Site site = siteDao.find(7);
            site.setName("Site modifié");
            siteDao.update(site);

            Set<Sector> sectors = sectorDao.findAllBySite(site);
            for(Sector sector:sectors)
            {
                sector.setName("secteur modifié");
                sectorDao.update(sector);
                Set<Way> ways = wayDao.findAllBySector(sector);
                for(Way way : ways){
                    way.setName("voie modifié");
                    wayDao.update(way);
                    Set<Length> lengths = lengthDao.findAllByWay(way);
                    for(Length length : lengths){
                        length.setName("longueur modifiée");
                        lengthDao.update(length);
                        Set<Point> points = pointDao.findAllByLength(length);
                        for(Point point : points)
                        {
                            point.setName("Point modifié");
                            pointDao.update(point);
                        }
                    }
                }
            }

        }catch(DaoException e){
            e.printStackTrace();
        }


    }
}
