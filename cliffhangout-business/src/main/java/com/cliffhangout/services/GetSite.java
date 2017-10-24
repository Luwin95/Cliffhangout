package com.cliffhangout.services;

import com.cliffhangout.beans.*;
import com.cliffhangout.dao.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetSite {
    private SiteDao siteDao;
    public GetSite()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.siteDao = daoFactory.getSiteDao();
    }

    public Site displaySite()
    {
        Site site = new Site();
        try{
            site= siteDao.find(3);
        }catch(DaoException e){
            e.printStackTrace();
        }


        return site;
    }
}
