package com.cliffhangout.services;

import com.cliffhangout.beans.*;
import com.cliffhangout.dao.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetSite {
    private SiteDao siteDao;
    private SectorDao sectorDao;
    private WayDao wayDao;
    private LengthDao lengthDao;
    private PointDao pointDao;

    public GetSite()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.siteDao = daoFactory.getSiteDao();
        this.sectorDao = daoFactory.getSectorDao();
        this.wayDao = daoFactory.getWayDao();
        this.lengthDao = daoFactory.getLengthDao();
        this.pointDao = daoFactory.getPointDao();
    }

    public List<Object> displaySite()
    {
        List<Object> entities = new ArrayList<>();
        Site site = new Site();
        Set<Sector> sectors = new HashSet<>();
        Set<Way> ways = new HashSet<>();
        Set<Length> lengths = new HashSet<>();
        Set<Point> points = new HashSet<>();
        try{
            site= siteDao.find(3);
            sectors= sectorDao.findAllBySite(site);
            for(Sector sector : sectors){
                Set<Way> tempWays = wayDao.findAllBySector(sector);
                ways.addAll(tempWays);
            }

            for(Way way:ways){
                Set<Length> tempLengths = lengthDao.findAllByWay(way);
                lengths.addAll(tempLengths);
            }

            for(Length length:lengths){
                Set<Point> tempPoints = pointDao.findAllByLength(length);
                points.addAll(tempPoints);
            }
        }catch(DaoException e){
            e.printStackTrace();
        }
        entities.add(site);
        entities.add(sectors);
        entities.add(ways);
        entities.add(lengths);
        entities.add(points);

        return entities;
    }
}
