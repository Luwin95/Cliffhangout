package com.cliffhangout.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;

import java.sql.ResultSet;
import java.util.List;

public interface SectorDao {
    void create(Sector sector) throws DaoException;
    void update(Sector sector) throws DaoException;
    void delete(Sector sector) throws DaoException;
    void deleteAllBySite(Site site) throws DaoException;
    Sector find(int id, Site site) throws DaoException;
    List<Sector> findAllBySite(Site site) throws DaoException;
    Sector buildSector(ResultSet resultat, Site site) throws DaoException;
}
