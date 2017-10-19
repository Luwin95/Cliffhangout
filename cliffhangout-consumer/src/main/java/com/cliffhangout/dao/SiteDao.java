package com.cliffhangout.dao;

import com.cliffhangout.beans.Site;
import java.util.List;

public interface SiteDao {
    void create(Site site) throws DaoException;
    void update(Site site) throws DaoException;
    void delete(Site site) throws DaoException;
    List<Site> findAllSites() throws DaoException;
}
