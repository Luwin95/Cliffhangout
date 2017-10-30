package com.cliffhangout.dao;

import com.cliffhangout.beans.Image;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;

import java.sql.ResultSet;
import java.util.List;

public interface ImageDao {
    void create(Image image) throws DaoException;
    void update(Image image) throws DaoException;
    void delete(Image image) throws DaoException;
    Image find(int id) throws DaoException;
    List<Image> findAllBySite(Site site) throws DaoException;
    List<Image> findAllBySector(Sector sector) throws DaoException;
    Image buildImage(ResultSet resultSet) throws DaoException;
}
