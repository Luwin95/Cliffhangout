package com.cliffhangout.consumer.contract;

import com.cliffhangout.consumer.contract.dao.*;

public interface DaoFactory {

    CommentDao getCommentDao();
    void setCommentDao(CommentDao pCommentDao);
    DepartementDao getDepartementDao();
    void setDepartementDao(DepartementDao pDepartementDao);
    ImageDao getImageDao();
    void setImageDao(ImageDao pImageDao);
    LengthDao getLengthDao();
    void setLengthDao(LengthDao pLengthDao);
    PointDao getPointDao();
    void setPointDao(PointDao pPointDao);
    QuotationDao getQuotationDao();
    void setQuotationDao(QuotationDao pQuotationDao);
    RegionDao getRegionDao();
    void setRegionDao(RegionDao pRegionDao);
    SectorDao getSectorDao();
    void setSectorDao(SectorDao pSectorDao);
    SiteDao getSiteDao();
    void setSiteDao(SiteDao pSiteDao);
    TopoDao getTopoDao();
    void setTopoDao(TopoDao pTopoDao);
    UserDao getUserDao();
    void setUserDao(UserDao pUserDao);
    WayDao getWayDao();
    void setWayDao(WayDao pWayDao);
    BorrowDao getBorrowDao();
    void setBorrowDao(BorrowDao borrowDao);
}
