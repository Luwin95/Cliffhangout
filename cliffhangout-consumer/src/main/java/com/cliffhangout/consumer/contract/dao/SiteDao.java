package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

public interface SiteDao {
    void create(Site site);
    void createSiteTopo(Site site, Topo topo);
    void update(Site site);
    void delete(int id);
    void deleteSiteTopo(Site site);
    Site find(int id);
    List<Site> findAllSites();
    List<Site> findAllByTopo(Topo topo);
    List<Site> findAllBySearchCriteria(String sqlStatement);
    List<Site> findLastTen();
    List<Site> findCreatorSites(User user);
}
