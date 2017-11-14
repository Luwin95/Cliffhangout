package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Site;
import com.cliffhangout.consumer.contract.dao.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteRM implements RowMapper<Site> {
    private UserDao userDao;
    private DepartementDao departementDao;
    private ImageDao imageDao;
    private SectorDao sectorDao;
    private CommentDao commentDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public DepartementDao getDepartementDao() {
        return departementDao;
    }

    public void setDepartementDao(DepartementDao departementDao) {
        this.departementDao = departementDao;
    }

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public SectorDao getSectorDao() {
        return sectorDao;
    }

    public void setSectorDao(SectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site();
        site.setId(rs.getInt("id"));
        site.setName(rs.getString("name"));
        site.setDescription(rs.getString("description"));
        site.setLocation(rs.getString("location"));
        site.setPostcode(rs.getString("postcode"));
        site.setLatitude(rs.getFloat("latitude"));
        site.setLongitude(rs.getFloat("longitude"));
        site.setCreator(userDao.find(rs.getInt("user_account_id")));
        site.setDepartement(departementDao.find(rs.getString("departement_code")));
        site.setRegion(site.getDepartement().getRegion());
        site.setImages(imageDao.findAllBySite(site));
        site.setSectors(sectorDao.findAllBySite(site));
        site.setComments(commentDao.findAllBySite(site));
        return site;
    }
}
