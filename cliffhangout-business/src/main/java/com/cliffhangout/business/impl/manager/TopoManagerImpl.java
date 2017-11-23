package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.business.contract.manager.TopoManager;
import org.apache.commons.io.FileUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
        topo.setBorrowed(false);
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
            return getDaoFactory().getTopoDao().find(id);
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
    }
}
