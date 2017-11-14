package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Image;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface ImageDao {
    void create(Image image);
    void update(Image image);
    void delete(Image image);
    Image find(int id);
    List<Image> findAllBySite(Site site);
    List<Image> findAllBySector(Sector sector);
}
