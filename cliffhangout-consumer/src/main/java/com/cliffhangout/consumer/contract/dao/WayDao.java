package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface WayDao {
    void create(Way way);
    void update(Way way);
    void delete(Way way);
    void deleteAllBySector(Sector sector);
    Way find(int id, Sector sector);
    List<Way> findAllBySector(Sector sector);
}
