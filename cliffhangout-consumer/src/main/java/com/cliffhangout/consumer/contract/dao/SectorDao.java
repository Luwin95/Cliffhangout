package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface SectorDao {
    void create(Sector sector);
    void update(Sector sector);
    void delete(Sector sector);
    void deleteAllBySite(Site site);
    Sector find(int id);
    List<Sector> findAllBySite(Site site);
}
