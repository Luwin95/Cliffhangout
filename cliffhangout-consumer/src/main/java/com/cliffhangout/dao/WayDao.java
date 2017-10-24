package com.cliffhangout.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Way;
import java.sql.ResultSet;
import java.util.List;

public interface WayDao {
    void create(Way way) throws DaoException;
    void update(Way way) throws DaoException;
    void delete(Way way) throws DaoException;
    void deleteAllBySector(Sector sector) throws DaoException;
    Way find(int id, Sector sector)throws DaoException;
    List<Way> findAllBySector(Sector sector) throws DaoException;
    Way buildWay(ResultSet resultat, Sector sector) throws DaoException;

}
