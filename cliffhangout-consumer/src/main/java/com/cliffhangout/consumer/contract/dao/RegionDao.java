package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Region;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface RegionDao {
    Region find(int id);
    List<Region> findAll();
}
