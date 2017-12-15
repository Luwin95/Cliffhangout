package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.User;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public interface SiteManager {
    Site displaySite(int id);
    List<Site> displayLastTenSite();
    List<Site> search(Hashtable criterias);
    List<Site> displayCreatorSites(User user);
    List<Site> displaySitesChosen(List<String> siteToAdd);
    List<Site> displayAllSites();
    void buildSiteDependencies(Site site);
    void addSite(Site site);
    void addSite(Site site, List<File> uploads, List<String> uploadsContentType, List<String> uploadsFileName);
    void addSiteDependencies(Site site);
    void addSiteImage(Site site, List<File> uploads, List<String> uploadsContentType, List<String> uploadsFileName);
    void testAddSiteImage(Site site, List<File> uploads, List<String> uploadsContentType, List<String> uploadsFileName);
    void updateSite(Site site);
    void updateSite(Site site, List<File> uploads, List<String> uploadsContentType, List<String> uploadsFileName);
    void updateSiteDependencies(Site site);
    void identifyDepartement(Site site);
    void deleteSite(int id);
    void deleteSiteDependencies(Site site);
    void deleteSiteImage(int idImage, Map<String, Object> session);
    void isInSession(List<Site> sitesInSession, List<Site> sitesToCheck);
    void removeSitesChosen(List<String> sitesToRemove, Map<String, Object> session);
}
