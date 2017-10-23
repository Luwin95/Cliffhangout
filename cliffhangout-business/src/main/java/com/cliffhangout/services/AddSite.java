package com.cliffhangout.services;

import com.cliffhangout.beans.*;
import com.cliffhangout.dao.*;

public class AddSite {

    private SiteDao siteDao;
    private SectorDao sectorDao;
    private WayDao wayDao;
    private LengthDao lengthDao;
    private PointDao pointDao;
    private UserDao userDao;

    public AddSite()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.siteDao = daoFactory.getSiteDao();
        this.sectorDao = daoFactory.getSectorDao();
        this.wayDao = daoFactory.getWayDao();
        this.lengthDao = daoFactory.getLengthDao();
        this.pointDao = daoFactory.getPointDao();
        this.userDao = daoFactory.getUserDao();
    }

    public void newSite(){
        try{

            Site site = new Site();
            site.setName("test");
            site.setDescription("test site 1");
            site.setLocation("Eaubonne");
            site.setPostcode(95600);
            site.setLatitude(49);
            site.setLongitude(2.28333);
            site.setCreator(userDao.find(1));

            Sector sector1= new Sector();
            sector1.setName("sector1");
            sector1.setDescription("sector 1 du site 1");
            sector1.setSite(site);

            Sector sector2= new Sector();
            sector2.setName("sector2");
            sector2.setDescription("sector 2 du site 1");
            sector2.setSite(site);

            Way way1 = new Way();
            way1.setName("Voie 1 du sector 1 du site 1");
            way1.setHeight(1.500);
            way1.setQuotation("5c");
            way1.setPointsNb(4);
            way1.setSector(sector1);

            Way way2 = new Way();
            way2.setName("Voie 2 du sector 1 du site 1");
            way2.setHeight(2.500);
            way2.setQuotation("4a");
            way2.setPointsNb(4);
            way2.setSector(sector1);

            Way way3 = new Way();
            way3.setName("Voie 1 du sector 2 du site 1");
            way3.setHeight(1.500);
            way3.setQuotation("5c");
            way3.setPointsNb(4);
            way3.setSector(sector2);

            Way way4 = new Way();
            way4.setName("Voie 2 du sector 2 du site 1");
            way4.setHeight(2.500);
            way4.setQuotation("4a");
            way4.setPointsNb(4);
            way4.setSector(sector2);

            Length length1 = new Length();
            length1.setName("length 1");
            length1.setDescription("length 1 de la Voie 1 du sector 1 du site 1");
            length1.setWay(way1);

            Length length2 = new Length();
            length2.setName("length 2");
            length2.setDescription("length 2 de la Voie 1 du sector 1 du site 1");
            length2.setWay(way1);

            Length length3 = new Length();
            length3.setName("length 3");
            length3.setDescription("length 1 de la Voie 2 du sector 1 du site 1");
            length3.setWay(way2);

            Length length4 = new Length();
            length4.setName("length 2");
            length4.setDescription("length 2 de la Voie 2 du sector 1 du site 1");
            length4.setWay(way2);

            Length length5 = new Length();
            length5.setName("length 1");
            length5.setDescription("length  1 de la Voie 1 du sector 2 du site 1");
            length5.setWay(way3);

            Length length6 = new Length();
            length6.setName("length 2");
            length6.setDescription("length 2 de la Voie 1 du sector 2 du site 1");
            length6.setWay(way3);

            Length length7 = new Length();
            length7.setName("length 1");
            length7.setDescription("length 1 de la Voie 2 du sector 2 du site 1");
            length7.setWay(way4);

            Length length8 = new Length();
            length8.setName("length 2");
            length8.setDescription("length 2 de la Voie 2 du sector 2 du site 1");
            length8.setWay(way4);

            Point point1 = new Point();
            point1.setName("Point 1");
            point1.setDescription("point 1 de la length 1 de la Voie 1 du sector 1 du site 1");
            point1.setLength(length1);

            Point point2 = new Point();
            point2.setName("Point 2");
            point2.setDescription("point 2 de la length 1 de la Voie 1 du sector 1 du site 1");
            point2.setLength(length1);

            Point point3 = new Point();
            point3.setName("Point 1");
            point3.setDescription("point 1 de la length 2 de la Voie 1 du sector 1 du site 1");
            point3.setLength(length2);

            Point point4 = new Point();
            point4.setName("Point 2");
            point4.setDescription("point 2 de la length 2 de la Voie 1 du sector 1 du site 1");
            point4.setLength(length2);

            Point point5 = new Point();
            point5.setName("Point 1");
            point5.setDescription("point 1 de la length 1 de la Voie 2 du sector 1 du site 1");
            point5.setLength(length3);

            Point point6 = new Point();
            point6.setName("Point 2");
            point6.setDescription("point 2 de la length 1 de la Voie 2 du sector 1 du site 1");
            point6.setLength(length3);

            Point point7 = new Point();
            point7.setName("Point 1");
            point7.setDescription("point 1 de la length 2 de la Voie 2 du sector 1 du site 1");
            point7.setLength(length4);

            Point point8 = new Point();
            point8.setName("Point 2");
            point8.setDescription("point 2 de la length 2 de la Voie 2 du sector 1 du site 1");
            point8.setLength(length4);

            Point point9 = new Point();
            point9.setName("Point 1");
            point9.setDescription("point 1 de la length 1 de la Voie 1 du sector 2 du site 1");
            point9.setLength(length5);

            Point point10 = new Point();
            point10.setName("Point 2");
            point10.setDescription("point 2 de la length 1 de la Voie 1 du sector 2 du site 1");
            point10.setLength(length5);

            Point point11 = new Point();
            point11.setName("Point 1");
            point11.setDescription("point 1 de la length 2 de la Voie 1 du sector 2 du site 1");
            point11.setLength(length6);

            Point point12 = new Point();
            point12.setName("Point 2");
            point12.setDescription("point 2 de la length 2 de la Voie 1 du sector 2 du site 1");
            point12.setLength(length6);

            Point point13 = new Point();
            point13.setName("Point 1");
            point13.setDescription("point 1 de la length 1 de la Voie 2 du sector 2 du site 1");
            point13.setLength(length7);

            Point point14 = new Point();
            point14.setName("Point 2");
            point14.setDescription("point 2 de la length 1 de la Voie 2 du sector 2 du site 1");
            point14.setLength(length7);

            Point point15 = new Point();
            point15.setName("Point 1");
            point15.setDescription("point 1 de la length 2 de la Voie 2 du sector 2 du site 1");
            point15.setLength(length8);

            Point point16 = new Point();
            point16.setName("Point 2");
            point16.setDescription("point 2 de la length 2 de la Voie 2 du sector 2 du site 1");
            point16.setLength(length8);

            siteDao.create(site);
            sectorDao.create(sector1);
            sectorDao.create(sector2);
            wayDao.create(way1);
            wayDao.create(way2);
            wayDao.create(way3);
            wayDao.create(way4);
            lengthDao.create(length1);
            lengthDao.create(length2);
            lengthDao.create(length3);
            lengthDao.create(length4);
            lengthDao.create(length5);
            lengthDao.create(length6);
            lengthDao.create(length7);
            lengthDao.create(length8);
            pointDao.create(point1);
            pointDao.create(point2);
            pointDao.create(point3);
            pointDao.create(point4);
            pointDao.create(point5);
            pointDao.create(point6);
            pointDao.create(point7);
            pointDao.create(point8);
            pointDao.create(point9);
            pointDao.create(point10);
            pointDao.create(point11);
            pointDao.create(point12);
            pointDao.create(point13);
            pointDao.create(point14);
            pointDao.create(point15);
            pointDao.create(point16);
        }catch(DaoException e){
            e.printStackTrace();

        }
    }
}
