package com.cliffhangout.dao;

import com.cliffhangout.beans.Site;
import java.util.List;

public interface SiteDao {
    void create(Site site);
    List<Site> findAllSites();
}
