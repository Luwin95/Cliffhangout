package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.*;
import com.cliffhangout.business.contract.manager.CommentManager;
import com.cliffhangout.business.contract.manager.SiteManager;
import org.apache.commons.io.FileUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SiteManagerImpl extends AbstractManagerImpl implements SiteManager {

    private CommentManager commentManager;

    public CommentManager getCommentManager() {
        return commentManager;
    }

    public void setCommentManager(CommentManager commentManager) {
        this.commentManager = commentManager;
    }

    @Override
    public Site displaySite(int id)
    {
        try{
            Site site= getDaoFactory().getSiteDao().find(id);
            buildSiteDependencies(site);
            if(site.getComments() != null)
            {
                List<Comment> sortedComments = getCommentManager().sortComments(site.getComments());
                site.setComments(sortedComments);
            }
            return site;
        }catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Site> displayLastTenSite()
    {
        try{
            List<Site> sites = getDaoFactory().getSiteDao().findLastTen();
            for(Site site : sites)
            {
                buildSiteDependencies(site);
            }
            return sites;
        }catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }


    @Override
    public List<Site> search(Hashtable criterias)
    {
        try{
            String sqlStatement = "SELECT site.*, user_account.*, user_account.user_account_id AS user_id, image.*, image.image_id AS imageId, region.region_name, departement.departement_name " +
                    "FROM site " +
                    "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                    "LEFT JOIN image ON image.image_id = user_account.image_id " +
                    "LEFT JOIN region ON site.region_id=region.region_id " +
                    "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                    "WHERE";
            List<String> sqlCriterias= new ArrayList<>();
            List<Site> sites = new ArrayList<Site>();
            Enumeration enumCriteria = criterias.keys();
            //Parcours des critères choisies par l'utilisateur
            while (enumCriteria.hasMoreElements()) {
                String key = (String) enumCriteria.nextElement();
                //Critère de sélection sur le nom du site
                if(criterias.containsKey("site-name") && key.equals("site-name") && !criterias.get(key).equals(""))
                {
                    String siteName = (String) criterias.get(key);
                    String siteNameCap = siteName.substring(0, 1).toUpperCase() + siteName.toLowerCase().substring(1);
                    sqlCriterias.add(" (name LIKE '%"+siteName+"%' OR name LIKE '%"+siteName.toLowerCase()+"%' OR name LIKE '%"+siteName.toUpperCase()+"%' OR name LIKE '%"+siteNameCap+"%')") ;
                }

                //Critère de sélection sur la localisation
                if(criterias.containsKey("criteria-location") && key.equals("criteria-location") && criterias.get(key).equals(true))
                {
                    //Critère de sélection sur une région française
                    if(criterias.containsKey("criteria-region") && !criterias.get("criteria-region").equals("0"))
                    {
                        sqlCriterias.add("( region.region_id = "+criterias.get("criteria-region")+")");
                    }
                    //critère de sélection sur un département français
                    if(criterias.containsKey("criteria-departement") && !criterias.get("criteria-departement").equals("0"))
                    {
                        sqlCriterias.add("( departement.departement_code = '"+criterias.get("criteria-departement")+"')");
                    }
                }
            }


            //Si aucun critère n'a été remplie, on récupère tous les sites
            if(sqlCriterias.isEmpty()){
                sqlStatement = "SELECT site.*, user_account.*, user_account.user_account_id AS user_id, image.*, image.image_id AS imageId, region.region_name, departement.departement_name " +
                        "FROM site " +
                        "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                        "LEFT JOIN image ON image.image_id = user_account.image_id " +
                        "LEFT JOIN region ON site.region_id=region.region_id " +
                        "LEFT JOIN departement ON site.departement_code = departement.departement_code ";
            }else{
                //Sinon on ajoute tous les critères de sélection à la requête en les séparant par un AND
                for(String sqlCriteria : sqlCriterias)
                {
                    System.out.println(sqlCriteria);
                    sqlStatement = sqlStatement + sqlCriteria;
                    if(!sqlCriteria.equals(sqlCriterias.get(sqlCriterias.size()-1)))
                    {
                        sqlStatement += " AND ";
                    }
                }
            }
            sqlStatement+= " ORDER BY site.site_id;";
            sites = getDaoFactory().getSiteDao().findAllBySearchCriteria(sqlStatement);

            for(Site site : sites)
            {
                buildSiteDependencies(site);
            }
            //Critère de sélection sur la cotation maximale ou minimale du site
            if(criterias.containsKey("criteria-cotation") && criterias.get("criteria-cotation").equals(true))
            {
                //Critère de sélection sur la cotation minimale du site
                if(criterias.containsKey("criteria-cotation-min-value"))
                {
                    String cotationMin = (String) criterias.get("criteria-cotation-min-value");

                    Quotation quotation = getDaoFactory().getQuotationDao().findByName(cotationMin);
                    int cpt = 0;
                    List<Site> sitesToRemove = new ArrayList<>();
                    for(Site site: sites)
                    {
                        for(Sector sector : site.getSectors())
                        {
                            for(Way way : sector.getWays())
                            {
                                if(way.getQuotation().getDifficulty() < quotation.getDifficulty())
                                {
                                    sitesToRemove.add(site);
                                }
                            }
                        }
                    }
                    sites.removeAll(sitesToRemove);
                }
                //Critère de sélection sur la cotation maximale du site
                if(criterias.containsKey("criteria-cotation-max-value"))
                {
                    String cotationMax = (String) criterias.get("criteria-cotation-max-value");
                    Quotation quotation = getDaoFactory().getQuotationDao().findByName(cotationMax);
                    List<Site> sitesToRemove = new ArrayList<>();
                    for(Site site: sites)
                    {
                        for(Sector sector : site.getSectors())
                        {
                            for(Way way : sector.getWays())
                            {
                                if(way.getQuotation().getDifficulty() > quotation.getDifficulty())
                                {
                                    sitesToRemove.add(site);
                                }
                            }
                        }
                    }
                    sites.removeAll(sitesToRemove);
                }
            }
            if(criterias.containsKey("criteria-ways") && criterias.get("criteria-ways").equals(true))
            {
                int wayNbMin;
                int wayNbMax;

                List<Site> sitesToRemove = new ArrayList<>();

                if(criterias.get("criteria-way-number-min").equals(""))
                {
                    wayNbMin =0;
                }else{
                    wayNbMin = Integer.parseInt((String) criterias.get("criteria-way-number-min"));
                }

                if(criterias.get("criteria-way-number-max").equals(""))
                {
                    wayNbMax =0;
                }else{

                    wayNbMax = Integer.parseInt((String) criterias.get("criteria-way-number-max"));
                }

                if(wayNbMax < wayNbMin && wayNbMax>0) {
                    sites = new ArrayList<>();
                }else if(wayNbMax==0 && wayNbMin>0)
                {
                    for(Site site: sites)
                    {
                        int nbWays = 0;
                        for(Sector sector : site.getSectors())
                        {
                            nbWays += sector.getWays().size();
                        }
                        if(nbWays<wayNbMin)
                        {
                            sitesToRemove.add(site);
                        }
                    }
                    sites.removeAll(sitesToRemove);
                }else{
                    for(Site site: sites)
                    {
                        int nbWays = 0;
                        for(Sector sector : site.getSectors())
                        {
                            nbWays += sector.getWays().size();
                        }
                        if(nbWays>wayNbMax ||nbWays<wayNbMin)
                        {
                            sitesToRemove.add(site);
                        }
                    }
                    sites.removeAll(sitesToRemove);
                }
            }
            return sites;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public List<Site> displayCreatorSites(User user) {
        try{
            List<Site> sites = getDaoFactory().getSiteDao().findCreatorSites(user);
            for(Site site : sites)
            {
                buildSiteDependencies(site);
            }
            return sites;
        }catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Site> displaySitesChosen(List<String> siteToAdd) {
        List<Site> sitesToreturn = new ArrayList<>();
        for(String siteId : siteToAdd)
        {
            if(siteId!=null)
            {
                sitesToreturn.add(getDaoFactory().getSiteDao().find(Integer.parseInt(siteId)));
            }
        }
        return sitesToreturn;
    }

    @Override
    public List<Site> displayAllSites() {
        List<Site> sites = getDaoFactory().getSiteDao().findAllSites();
        for(Site site : sites)
        {
            buildSiteDependencies(site);
        }
        return sites;
    }

    @Override
    public void buildSiteDependencies(Site site)
    {
        site.setImages(getDaoFactory().getImageDao().findAllBySite(site));
        site.setSectors(getDaoFactory().getSectorDao().findAllBySite(site));
        site.setComments(getDaoFactory().getCommentDao().findAllBySite(site));
        for(Sector sector : site.getSectors())
        {
            sector.setWays(getDaoFactory().getWayDao().findAllBySector(sector));
            for(Way way : sector.getWays())
            {
                way.setLengths(getDaoFactory().getLengthDao().findAllByWay(way));
                for(Length length : way.getLengths())
                {
                    length.setPoints(getDaoFactory().getPointDao().findAllByLength(length));
                }
            }
        }
    }

    @Override
    public void addSite(Site site) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                identifyDepartement(site);
                getDaoFactory().getSiteDao().create(site);
                addSiteDependencies(site);
            }
        });
    }

    @Override
    public void addSite(Site site, List<File> uploads, List<String> uploadsContentType, List<String> uploadsFileName) {
        addSite(site);
        //addSiteImage(site, uploads, uploadsContentType, uploadsFileName);
        testAddSiteImage(site, uploads, uploadsContentType, uploadsFileName);
    }

    @Override
    public void addSiteDependencies(Site site) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                for (Sector sector : site.getSectors())
                {
                    sector.setSiteId(site.getId());
                    getDaoFactory().getSectorDao().create(sector);
                    for(Way way: sector.getWays())
                    {
                        way.setSectorId(sector.getId());
                        int wayPointNb=0;
                        for(Length length : way.getLengths())
                        {
                            wayPointNb += length.getPoints().size();
                        }
                        way.setPointsNb(wayPointNb);
                        getDaoFactory().getWayDao().create(way);
                        for(Length length : way.getLengths())
                        {
                            length.setWayId(way.getId());
                            getDaoFactory().getLengthDao().create(length);
                            for(Point point : length.getPoints())
                            {
                                point.setLengthId(length.getId());
                                getDaoFactory().getPointDao().create(point);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void addSiteImage(Site site, List<File> uploads, List<String> uploadsContentType, List<String> uploadsFileName) {
        int i=0;
        for(File upload : uploads)
        {
            String userDir = getUploadDirectory().replaceAll("\\\\", "/");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_H_mm_ss");
            Date date = new Date();
            String path=dateFormat.format(date)+ UUID.randomUUID().toString()+"."+uploadsContentType.get(i).substring(6);
            Image image = new Image();
            image.setPath(path);
            image.setTitle("Image du site d'escalade "+site.getName() );
            image.setAlt("Image du site d'escalade "+site.getName()+ " situé dans la ville de "+site.getLocation()+" dans le département "+site.getDepartement().getName());
            String fileName = "/images/site/"+path;
            String fullfilename = userDir+fileName;
            try
            {
                TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
                vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus
                                                                        pTransactionStatus) {
                        getDaoFactory().getImageDao().createSiteImage(image, site);
                    }
                });
            }finally{
                File importedImage = new File(fullfilename);
                try{
                    FileUtils.copyFile(uploads.get(i), importedImage);
                }catch(IOException e){
                }
            }
            site.getImages().add(image);
            i++;
        }
    }

    @Override
    public void testAddSiteImage(Site site, List<File> uploads, List<String> uploadsContentType, List<String> uploadsFileName) {
        int i=0;
        String desc="";
        for(File file : uploads){
            try {
                String userDir = getUploadDirectory().replaceAll("\\\\", "/");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_H_mm_ss");
                Date date = new Date();
                String path=dateFormat.format(date)+ UUID.randomUUID().toString()+"."+uploadsContentType.get(i).substring(6);
                Image image = new Image();
                image.setPath(path);
                image.setTitle("Image du site d'escalade "+site.getName() );
                image.setAlt("Image du site d'escalade "+site.getName()+ " situé dans la ville de "+site.getLocation()+" dans le département "+site.getDepartement().getName());
                String fileName = "/images/site/"+path;
                String fullfilename = userDir+fileName;
                FileInputStream fis = new FileInputStream(file);
                String contentType = uploadsContentType.get(i);
                System.out.println(contentType);
                TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
                vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus
                                                                        pTransactionStatus) {
                        getDaoFactory().getImageDao().createSiteImage(image, site);
                    }
                });
                FileOutputStream fos = new FileOutputStream(fullfilename);
                byte[] b = new byte[1024];
                while(fis.read(b) != -1){
                    fos.write(b);
                }
                desc += fileName +", ";
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    @Override
    public void identifyDepartement(Site site) {
        if(site.getPostcode().substring(0,2).equals("97"))
        {
            site.setDepartement(getDaoFactory().getDepartementDao().find(site.getPostcode().substring(0,3)));
        }else{
            site.setDepartement(getDaoFactory().getDepartementDao().find(site.getPostcode().substring(0,2)));
        }
        site.setRegion(site.getDepartement().getRegion());
    }

    @Override
    public void updateSite(Site site) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                identifyDepartement(site);
                getDaoFactory().getSiteDao().update(site);
                updateSiteDependencies(site);
            }
        });
    }

    @Override
    public void updateSite(Site site, List<File> uploads, List<String> uploadsContentType, List<String> uploadsFileName) {
        addSiteImage(site, uploads, uploadsContentType, uploadsFileName);
        updateSite(site);
    }

    @Override
    public void updateSiteDependencies(Site site) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                for (Sector sector : site.getSectors())
                {
                    sector.setSiteId(site.getId());
                    getDaoFactory().getSectorDao().update(sector);
                    for(Way way: sector.getWays())
                    {
                        way.setSectorId(sector.getId());
                        getDaoFactory().getWayDao().update(way);
                        for(Length length : way.getLengths())
                        {
                            length.setWayId(way.getId());
                            getDaoFactory().getLengthDao().update(length);
                            for(Point point : length.getPoints())
                            {
                                point.setLengthId(length.getId());
                                getDaoFactory().getPointDao().update(point);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void deleteSite(int id) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                Site site = displaySite(id);
                deleteSiteDependencies(site);
                getDaoFactory().getSiteDao().delete(id);
            }
        });
    }

    @Override
    public void deleteSiteDependencies(Site site) {
        if(site.getSectors()!=null)
        {
            for(Sector sector : site.getSectors())
            {
                if(sector.getWays()!=null)
                {
                    for(Way way : sector.getWays())
                    {
                        if(way.getLengths() !=null)
                        {
                            for(Length length : way.getLengths())
                            {
                                if(length.getPoints() != null)
                                {
                                    for(Point point : length.getPoints())
                                    {
                                        getDaoFactory().getPointDao().delete(point);
                                    }
                                }
                                getDaoFactory().getLengthDao().delete(length);
                            }
                        }
                        getDaoFactory().getWayDao().delete(way);
                    }
                }
                getDaoFactory().getSectorDao().delete(sector);
            }
        }
        if(site.getImages()!=null)
        {
            for(Image image : site.getImages())
            {
                getDaoFactory().getImageDao().deleteSiteImage(image, site);
            }
        }
        if(site.getComments()!=null)
        {
            for(Comment comment : site.getComments())
            {
                getDaoFactory().getCommentDao().delete(comment);
            }
        }
        getDaoFactory().getSiteDao().deleteSiteTopo(site);
    }

    @Override
    public void deleteSiteImage(int idImage, Map<String, Object> session) {
        Site siteToEdit = (Site) session.get("site");
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                Image imageToDelete = new Image();
                for(Image image : siteToEdit.getImages())
                {
                    if(image.getId() == idImage)
                    {
                        imageToDelete = image;
                    }
                }
                getDaoFactory().getImageDao().deleteSiteImage(imageToDelete, siteToEdit);
                String userDir = getUploadDirectory().replaceAll("\\\\", "/");
                String imageToDeleteName = getUploadDirectory()+"/images/site/"+imageToDelete.getPath();
                File fileToDelete = new File(imageToDeleteName);
                FileUtils.deleteQuietly(fileToDelete);
                siteToEdit.getImages().remove(imageToDelete);
                session.remove("site");
                session.put("site", siteToEdit);
            }
        });

    }

    @Override
    public void isInSession(List<Site> sitesInSession, List<Site> sitesTocheck)
    {
        int cpt =0;
        List<Site> sitesToRemove = new ArrayList<>();
        for(Site siteInSession : sitesInSession)
        {
            for(Site siteTocheck : sitesTocheck)
            {
                if(siteTocheck.getId() == siteInSession.getId())
                {
                    cpt ++;
                    sitesToRemove.add(siteTocheck);
                }
            }
        }
        sitesTocheck.removeAll(sitesToRemove);
    }

    @Override
    public void removeSitesChosen(List<String> sitesChosen, Map<String, Object> session) {
        List<Site> tempSites = displaySitesChosen(sitesChosen);
        List<Site> sitesInSession = (List<Site>) session.get("sitesTopo");
        session.remove("sitesTopo");
        isInSession(tempSites, sitesInSession);
        if(sitesInSession.size()!=0)
        {
            session.put("sitesTopo", sitesInSession);
        }else{
            session.put("sitesTopo", null);
        }
    }
}
