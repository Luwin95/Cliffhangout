package com.cliffhangout.dao;

import com.cliffhangout.beans.Sector;

public interface SectorDao {
    void create(Sector sector) throws DaoException;
    void update(Sector sector) throws DaoException;
    void delete(Sector sector) throws DaoException;
}
