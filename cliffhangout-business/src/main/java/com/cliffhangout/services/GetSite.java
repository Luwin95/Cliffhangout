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

    public Site displaySite(int id)
    {
        Site site = new Site();
        try{
            site= siteDao.find(id);
            if(site.getComments() != null)
            {
                List<Comment> sortedComments = this.sortSiteComments(site.getComments());
                site.setComments(sortedComments);
            }
        }catch(DaoException e){
            e.printStackTrace();
        }
        return site;
    }

    public List<Site> displayLastTenSite()
    {
        List<Site> sites = new ArrayList<>();
        try{
            sites = siteDao.findLastTen();
        }catch(DaoException e){
            e.printStackTrace();
        }
        return sites;
    }

    private List<Comment> sortSiteComments(List<Comment> comments)
    {
        List<Comment> children = new ArrayList<Comment>();
        for(Comment comment : comments)
        {
            if(comment.getParent() != null)
            {
                children.add(comment);
                comments.remove(comment);
            }
        }
        for(Comment parent : comments)
        {
            for(Comment child : children)
            {
                if(parent.getId() == child.getParent().getId())
                {
                    parent.addChild(child);
                }
            }
        }
        return comments;
    }
}
