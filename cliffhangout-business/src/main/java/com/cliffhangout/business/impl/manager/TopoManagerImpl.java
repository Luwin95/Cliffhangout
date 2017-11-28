package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.business.contract.manager.TopoManager;
import org.apache.commons.io.FileUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TopoManagerImpl extends AbstractManagerImpl implements TopoManager {
    @Override
    public void addTopo(Topo topo, File upload, String uploadFileName, String uploadContentType, Map<String, Object> session) {
        String userDir = "E:\\P3\\cliffhangout-webapp\\src\\main\\webapp\\";
        userDir = userDir.replaceAll("\\\\", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_H_mm_ss");
        Date date = new Date();
        User creator = (User) session.get("sessionUser");
        List<Site> sitesTopo = (List<Site>) session.get("sitesTopo");

        topo.setFile(dateFormat.format(date)+uploadFileName);
        topo.setOwner(creator);
        try
        {
            TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
            vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus
                                                                    pTransactionStatus) {
                    getDaoFactory().getTopoDao().create(topo);
                    for(Site site: sitesTopo)
                    {
                        getDaoFactory().getSiteDao().createSiteTopo(site, topo);
                    }
                }
            });
        }finally{
            uploadFileName = "resources/topos/"+dateFormat.format(date)+uploadFileName;
            String fullfilename = userDir+uploadFileName;
            File importedTopo = new File(fullfilename);
            try{
                FileUtils.copyFile(upload, importedTopo);
            }catch(IOException e){
            }
            session.remove("sitesTopo");
        }
    }

    @Override
    public Topo displayTopo(int id) {
        try{
            Topo topo = getDaoFactory().getTopoDao().find(id);
            buildTopoDependencies(topo);
            return topo;
        }catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Topo> displayUserTopo(User user) {
        try{
            List<Topo> topos = getDaoFactory().getTopoDao().findAllByUser(user);
            for(Topo topo : topos)
            {
                buildTopoDependencies(topo);
            }
            return topos;
        }catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Topo> displayTopoToBorrow(User user) {
        try{
            List<Topo> topos = getDaoFactory().getTopoDao().findAllBorrowed(user);
            for(Topo topo : topos)
            {
                buildTopoDependencies(topo);
            }
            return topos;
        }catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Topo> displayAllTopo() {
        List<Topo> topos = getDaoFactory().getTopoDao().findAll();
        for (Topo topo : topos)
        {
            buildTopoDependencies(topo);
        }
        return topos;
    }

    @Override
    public void buildTopoDependencies(Topo topo) {
        topo.setSites(getDaoFactory().getSiteDao().findAllByTopo(topo));
    }

    @Override
    public void deleteTopo(int id) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                Topo topo = displayTopo(id);
                if(topo !=null)
                {
                    deleteTopoDependencies(topo);
                    getDaoFactory().getTopoDao().delete(topo);
                }
            }
        });
    }

    @Override
    public void deleteTopoDependencies(Topo topo) {
        getDaoFactory().getTopoDao().deleteSiteTopo(topo);
        getDaoFactory().getBorrowDao().deleteBorrowByTopo(topo);
    }

    @Override
    public void editTopo(Topo topo, Topo topoToEdit, Map<String, Object> session) {
        List<Site> sitesChosen = (List<Site>)session.get("sitesTopo");
        List<Site> commonSites= new ArrayList<>();
        List<Site> sitesTemp = new ArrayList<>();
        for(Site site: topoToEdit.getSites())
        {
            for(Site siteChosen: sitesChosen)
            {
                if(siteChosen.getId()== site.getId())
                {
                    commonSites.add(site);
                }
            }
        }
        List<Site> sitesToAdd= sitesChosen;
        for(Site site: commonSites)
        {
            for(Site siteChosen: sitesChosen)
            {
                if(siteChosen.getId()== site.getId())
                {
                    sitesTemp.add(siteChosen);
                }
            }
        }
        sitesToAdd.removeAll(sitesTemp);
        List<Site> sitesToRemove = topoToEdit.getSites();
        sitesToRemove.removeAll(commonSites);
        session.remove("sitesTopo");
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        topoToEdit.setName(topo.getName());
        topoToEdit.setDescription(topo.getDescription());
        topoToEdit.setBorrowed(topo.isBorrowed());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                getDaoFactory().getTopoDao().update(topoToEdit);
                if(sitesToAdd.size()>0)
                {
                    for(Site site: sitesToAdd)
                    {
                        getDaoFactory().getSiteDao().createSiteTopo(site, topoToEdit);
                    }
                }
                if(sitesToRemove.size()>0)
                {
                    for(Site site: sitesToRemove)
                    {
                        getDaoFactory().getSiteDao().deleteSiteTopo(site);
                    }
                }

            }
        });

    }

    @Override
    public void editTopo(Topo topo, Topo topoToEdit, File upload, String uploadFileName, String uploadContentType, Map<String, Object> session) {
        String userDir = "E:\\P3\\cliffhangout-webapp\\src\\main\\webapp\\";
        userDir = userDir.replaceAll("\\\\", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_H_mm_ss");
        Date date = new Date();
        String topoToDeleteFileName = userDir+"resources/topos/"+topoToEdit.getFile();
        topoToEdit.setFile(dateFormat.format(date)+uploadFileName);
        uploadFileName = "resources/topos/"+dateFormat.format(date)+uploadFileName;
        String fullfilename = userDir+uploadFileName;

        File topoToDelete = new File(topoToDeleteFileName);

        File importedTopo = new File(fullfilename);
        try{
            editTopo(topo, topoToEdit, session);
        }finally {
            try{
                FileUtils.deleteQuietly(topoToDelete);
                FileUtils.copyFile(upload, importedTopo);
            }catch(IOException e){
            }
        }
    }

    @Override
    public String borrowTopo(Topo topo, Date startDate, Date endDate, Map<String, Object> session) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        String result = vTransactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus status) {
                String result = null;
                Borrow borrow = null;
                try{
                    borrow = getDaoFactory().getBorrowDao().find( (User) session.get("sessionUser"), topo);
                }catch(EmptyResultDataAccessException e)
                {
                    borrow = null;
                }
                if(borrow!=null)
                {
                    try{
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date now = (sdf.parse(sdf.format(new Date())));
                        if(borrow.getEndDate().before(now) || borrow.getEndDate().equals(now))
                        {
                            getDaoFactory().getBorrowDao().deleteBorrow(topo, (User) session.get("sessionUser"));
                            getDaoFactory().getTopoDao().createBorrowing(topo, startDate, endDate, (User) session.get("sessionUser"));
                            result = null;
                        }else{
                            result = "Vous avez déjà un emprunt de ce topo qui n'est pas arrivé à échéance";
                        }
                    }catch(ParseException e){
                        e.printStackTrace();
                    }
                }else{
                    getDaoFactory().getTopoDao().createBorrowing(topo, startDate, endDate, (User) session.get("sessionUser"));
                    result = null;
                }
                return result;
            }

        });
        return result;
    }
}
