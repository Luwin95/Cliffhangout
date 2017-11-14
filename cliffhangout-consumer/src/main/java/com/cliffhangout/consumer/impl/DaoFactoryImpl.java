package com.cliffhangout.consumer.impl;

import com.cliffhangout.consumer.contract.dao.*;

public class DaoFactoryImpl {
    private CommentDao commentDao;
    private DepartementDao departementDao;
    private ImageDao imageDao;
    private LengthDao lengthDao;
    private PointDao pointDao;
    private QuotationDao quotationDao;
    private RegionDao regionDao;
    private SectorDao sectorDao;
    private SiteDao siteDao;
    private TopoDao topoDao;
    private UserDao userDao;
    private WayDao  wayDao;

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao pCommentDao) {
        commentDao = pCommentDao;
    }

    public DepartementDao getDepartementDao() {
        return departementDao;
    }

    public void setDepartementDao(DepartementDao pDepartementDao) {
        departementDao = pDepartementDao;
    }

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao pImageDao) {
        imageDao = pImageDao;
    }

    public LengthDao getLengthDao() {
        return lengthDao;
    }

    public void setLengthDao(LengthDao pLengthDao) {
        lengthDao = pLengthDao;
    }

    public PointDao getPointDao() {
        return pointDao;
    }

    public void setPointDao(PointDao pPointDao) {
        pointDao = pPointDao;
    }

    public QuotationDao getQuotationDao() {
        return quotationDao;
    }

    public void setQuotationDao(QuotationDao pQuotationDao) {
        quotationDao = pQuotationDao;
    }

    public RegionDao getRegionDao() {
        return regionDao;
    }

    public void setRegionDao(RegionDao pRegionDao) {
        regionDao = pRegionDao;
    }

    public SectorDao getSectorDao() {
        return sectorDao;
    }

    public void setSectorDao(SectorDao pSectorDao) {
        sectorDao = pSectorDao;
    }

    public SiteDao getSiteDao() {
        return siteDao;
    }

    public void setSiteDao(SiteDao pSiteDao) {
        siteDao = pSiteDao;
    }

    public TopoDao getTopoDao() {
        return topoDao;
    }

    public void setTopoDao(TopoDao pTopoDao) {
        topoDao = pTopoDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao pUserDao) {
        userDao = pUserDao;
    }

    public WayDao getWayDao() {
        return wayDao;
    }

    public void setWayDao(WayDao pWayDao) {
        wayDao = pWayDao;
    }
}
