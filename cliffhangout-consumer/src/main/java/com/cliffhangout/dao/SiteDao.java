package com.cliffhangout.dao;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;

import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

public interface SiteDao {
    void create(Site site) throws DaoException;
    void update(Site site) throws DaoException;
    void delete(Site site) throws DaoException;
    Site find(int id) throws DaoException;
    List<Site> findAllSites() throws DaoException;
    Set<Site> findAllByTopo(Topo topo) throws DaoException;
    Site buildSite(ResultSet resultat) throws DaoException;
}
