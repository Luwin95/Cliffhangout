package com.cliffhangout.consumer.impl;

import com.cliffhangout.consumer.contract.DaoFactory;
import com.cliffhangout.consumer.contract.dao.*;

public class DaoFactoryImpl implements DaoFactory {
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
    private BorrowDao borrowDao;

    @Override
    public CommentDao getCommentDao() {
        return commentDao;
    }

    @Override
    public void setCommentDao(CommentDao pCommentDao) {
        commentDao = pCommentDao;
    }

    @Override
    public DepartementDao getDepartementDao() {
        return departementDao;
    }

    @Override
    public void setDepartementDao(DepartementDao pDepartementDao) {
        departementDao = pDepartementDao;
    }

    @Override
    public ImageDao getImageDao() {
        return imageDao;
    }

    @Override
    public void setImageDao(ImageDao pImageDao) {
        imageDao = pImageDao;
    }

    @Override
    public LengthDao getLengthDao() {
        return lengthDao;
    }

    @Override
    public void setLengthDao(LengthDao pLengthDao) {
        lengthDao = pLengthDao;
    }

    @Override
    public PointDao getPointDao() {
        return pointDao;
    }

    @Override
    public void setPointDao(PointDao pPointDao) {
        pointDao = pPointDao;
    }

    @Override
    public QuotationDao getQuotationDao() {
        return quotationDao;
    }

    @Override
    public void setQuotationDao(QuotationDao pQuotationDao) {
        quotationDao = pQuotationDao;
    }

    @Override
    public RegionDao getRegionDao() {
        return regionDao;
    }

    @Override
    public void setRegionDao(RegionDao pRegionDao) {
        regionDao = pRegionDao;
    }

    @Override
    public SectorDao getSectorDao() {
        return sectorDao;
    }

    @Override
    public void setSectorDao(SectorDao pSectorDao) {
        sectorDao = pSectorDao;
    }

    @Override
    public SiteDao getSiteDao() {
        return siteDao;
    }

    @Override
    public void setSiteDao(SiteDao pSiteDao) {
        siteDao = pSiteDao;
    }

    @Override
    public TopoDao getTopoDao() {
        return topoDao;
    }

    @Override
    public void setTopoDao(TopoDao pTopoDao) {
        topoDao = pTopoDao;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public void setUserDao(UserDao pUserDao) {
        userDao = pUserDao;
    }

    @Override
    public WayDao getWayDao() {
        return wayDao;
    }

    @Override
    public void setWayDao(WayDao pWayDao) {
        wayDao = pWayDao;
    }

    @Override
    public BorrowDao getBorrowDao() {
        return borrowDao;
    }

    @Override
    public void setBorrowDao(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }
}
