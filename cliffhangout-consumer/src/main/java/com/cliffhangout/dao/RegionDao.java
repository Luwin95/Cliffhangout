package com.cliffhangout.dao;

import com.cliffhangout.beans.Region;

import java.sql.ResultSet;
import java.util.List;

public interface RegionDao {
    Region find(int id) throws DaoException;
    List<Region> findAll() throws DaoException;
    Region buildRegion(ResultSet resultSet) throws DaoException;
}
