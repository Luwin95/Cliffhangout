package com.cliffhangout.services;

import com.cliffhangout.beans.*;
import com.cliffhangout.dao.*;

public class AddSite {

    private SiteDao siteDao;
    private UserDao userDao;
    private DepartementDao departementDao;
    private QuotationDao quotationDao;

    public AddSite()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.siteDao = daoFactory.getSiteDao();
        this.userDao = daoFactory.getUserDao();
        this.departementDao = daoFactory.getDepartementDao();
        this.quotationDao = daoFactory.getQuotationDao();
    }

    public void newSite(){
        try{

            Site site = new Site();
            site.setName("Fort de Nantes");
            site.setDescription("test site 1");
            site.setLocation("Nantes");
            site.setPostcode("44000");
            site.setDepartement(departementDao.find("44"));
            site.setRegion(site.getDepartement().getRegion());
            site.setLatitude(47.2172500);
            site.setLongitude(-1.5533600);
            site.setCreator(userDao.find(1));

            Sector sector1= new Sector(site);
            sector1.setName("sector1");
            sector1.setDescription("sector 1 du site 1");
            site.addSector(sector1);

            Sector sector2= new Sector(site);
            sector2.setName("sector2");
            sector2.setDescription("sector 2 du site 1");
            site.addSector(sector2);

            Way way1 = new Way(sector1);
            way1.setName("Voie 1 du sector 1 du site 1");
            way1.setHeight(1.500);
            way1.setQuotation(quotationDao.findByName("6a"));
            way1.setPointsNb(4);
            sector1.addWay(way1);

            Way way2 = new Way(sector1);
            way2.setName("Voie 2 du sector 1 du site 1");
            way2.setHeight(2.500);
            way2.setQuotation(quotationDao.findByName("7b"));
            way2.setPointsNb(4);
            sector1.addWay(way2);

            Way way3 = new Way(sector2);
            way3.setName("Voie 1 du sector 2 du site 1");
            way3.setHeight(1.500);
            way3.setQuotation(quotationDao.findByName("8c"));
            way3.setPointsNb(4);
            sector2.addWay(way3);

            Way way4 = new Way(sector2);
            way4.setName("Voie 2 du sector 2 du site 1");
            way4.setHeight(2.500);
            way4.setQuotation(quotationDao.findByName("9a"));
            way4.setPointsNb(4);
            sector2.addWay(way4);

            Length length1 = new Length(way1);
            length1.setName("length 1");
            length1.setDescription("length 1 de la Voie 1 du sector 1 du site 1");
            way1.addLength(length1);

            Length length2 = new Length(way1);
            length2.setName("length 2");
            length2.setDescription("length 2 de la Voie 1 du sector 1 du site 1");
            way1.addLength(length2);

            Length length3 = new Length(way2);
            length3.setName("length 1");
            length3.setDescription("length 1 de la Voie 2 du sector 1 du site 1");
            way2.addLength(length3);

            Length length4 = new Length(way2);
            length4.setName("length 2");
            length4.setDescription("length 2 de la Voie 2 du sector 1 du site 1");
            way2.addLength(length4);

            Length length5 = new Length(way3);
            length5.setName("length 1");
            length5.setDescription("length  1 de la Voie 1 du sector 2 du site 1");
            way3.addLength(length5);

            Length length6 = new Length(way3);
            length6.setName("length 2");
            length6.setDescription("length 2 de la Voie 1 du sector 2 du site 1");
            way3.addLength(length6);

            Length length7 = new Length(way4);
            length7.setName("length 1");
            length7.setDescription("length 1 de la Voie 2 du sector 2 du site 1");
            length7.setWay(way4);
            way4.addLength(length7);

            Length length8 = new Length(way4);
            length8.setName("length 2");
            length8.setDescription("length 2 de la Voie 2 du sector 2 du site 1");
            length8.setWay(way4);
            way4.addLength(length8);

            Point point1 = new Point(length1);
            point1.setName("Point 1");
            point1.setDescription("point 1 de la length 1 de la Voie 1 du sector 1 du site 1");
            length1.addPoint(point1);

            Point point2 = new Point(length1);
            point2.setName("Point 2");
            point2.setDescription("point 2 de la length 1 de la Voie 1 du sector 1 du site 1");
            length1.addPoint(point2);

            Point point3 = new Point(length2);
            point3.setName("Point 1");
            point3.setDescription("point 1 de la length 2 de la Voie 1 du sector 1 du site 1");
            length2.addPoint(point3);

            Point point4 = new Point(length2);
            point4.setName("Point 2");
            point4.setDescription("point 2 de la length 2 de la Voie 1 du sector 1 du site 1");
            length2.addPoint(point4);

            Point point5 = new Point(length3);
            point5.setName("Point 1");
            point5.setDescription("point 1 de la length 1 de la Voie 2 du sector 1 du site 1");
            length3.addPoint(point5);

            Point point6 = new Point(length3);
            point6.setName("Point 2");
            point6.setDescription("point 2 de la length 1 de la Voie 2 du sector 1 du site 1");
            length3.addPoint(point6);

            Point point7 = new Point(length4);
            point7.setName("Point 1");
            point7.setDescription("point 1 de la length 2 de la Voie 2 du sector 1 du site 1");
            length4.addPoint(point7);

            Point point8 = new Point(length4);
            point8.setName("Point 2");
            point8.setDescription("point 2 de la length 2 de la Voie 2 du sector 1 du site 1");
            length4.addPoint(point8);

            Point point9 = new Point(length5);
            point9.setName("Point 1");
            point9.setDescription("point 1 de la length 1 de la Voie 1 du sector 2 du site 1");
            length5.addPoint(point9);

            Point point10 = new Point(length5);
            point10.setName("Point 2");
            point10.setDescription("point 2 de la length 1 de la Voie 1 du sector 2 du site 1");
            length5.addPoint(point10);

            Point point11 = new Point(length6);
            point11.setName("Point 1");
            point11.setDescription("point 1 de la length 2 de la Voie 1 du sector 2 du site 1");
            length6.addPoint(point11);

            Point point12 = new Point(length6);
            point12.setName("Point 2");
            point12.setDescription("point 2 de la length 2 de la Voie 1 du sector 2 du site 1");
            length6.addPoint(point12);

            Point point13 = new Point(length7);
            point13.setName("Point 1");
            point13.setDescription("point 1 de la length 1 de la Voie 2 du sector 2 du site 1");
            length7.addPoint(point13);

            Point point14 = new Point(length7);
            point14.setName("Point 2");
            point14.setDescription("point 2 de la length 1 de la Voie 2 du sector 2 du site 1");
            length7.addPoint(point14);

            Point point15 = new Point(length8);
            point15.setName("Point 1");
            point15.setDescription("point 1 de la length 2 de la Voie 2 du sector 2 du site 1");
            length8.addPoint(point15);

            Point point16 = new Point(length8);
            point16.setName("Point 2");
            point16.setDescription("point 2 de la length 2 de la Voie 2 du sector 2 du site 1");
            length8.addPoint(point16);

            siteDao.create(site);
        }catch(DaoException e){
            e.printStackTrace();

        }
    }
}
