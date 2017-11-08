package com.cliffhangout.services;

import com.cliffhangout.beans.*;
import com.cliffhangout.dao.*;

import java.util.*;

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
        Map<Integer, Comment> commentById = new HashMap<>();
        Iterator<Comment> iterator = comments.iterator();
        for(Comment comment : comments)
        {
            commentById.put(comment.getId(), comment);
        }
        while(iterator.hasNext())
        {
            Comment comment = iterator.next();
            if(comment.getParent() != null)
            {
                commentById.get(comment.getParent().getId()).addChild(comment);
                iterator.remove();
            }
        }
        return comments;
    }
}
