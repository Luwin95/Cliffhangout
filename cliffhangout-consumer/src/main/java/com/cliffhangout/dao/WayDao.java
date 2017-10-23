package com.cliffhangout.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Way;

import java.sql.ResultSet;
import java.util.Set;

public interface WayDao {
    void create(Way way) throws DaoException;
    void update(Way way) throws DaoException;
    void delete(Way way) throws DaoException;
    void deleteAllBySector(Sector sector) throws DaoException;
    Way find(int id)throws DaoException;
    Set<Way> findAllBySector(Sector sector) throws DaoException;
    Way buildWay(ResultSet resultat) throws DaoException;

}
