package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.User;

import java.util.Hashtable;
import java.util.List;

public interface SiteManager {
    Site displaySite(int id);
    List<Site> displayLastTenSite();
    List<Site> search(Hashtable criterias);
    List<Site> displayCreatorSites(User user);
    void buildSiteDependencies(Site site);
    void addSite(Site site);
    void addSiteDependencies(Site site);
    void identifyDepartement(Site site);
    void deleteSite(int id);
    void deleteSiteDependencies(Site site);
}
