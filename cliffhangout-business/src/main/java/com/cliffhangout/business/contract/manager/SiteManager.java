package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;

import java.util.Hashtable;
import java.util.List;

public interface SiteManager {
    Site displaySite(int id);
    List<Site> displayLastTenSite();
    List<Site> search(Hashtable criterias);
}
