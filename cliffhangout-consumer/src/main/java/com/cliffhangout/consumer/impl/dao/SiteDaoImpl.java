package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.*;
import com.cliffhangout.consumer.impl.rowmapper.SiteRM;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.List;

public class SiteDaoImpl extends AbstractDaoImpl implements SiteDao {
    private SiteRM siteRM;

    protected SiteRM getSiteRM() {
        return siteRM;
    }

    public void setSiteRM(SiteRM siteRM) {
        this.siteRM = siteRM;
    }

    @Override
    public void create(Site site){
        String vSQL = "INSERT INTO site(name, description, location, postcode, latitude, longitude, user_account_id, departement_code, region_id) VALUES(:name, :description, :location, :postcode, :latitude, :longitude, :user_account_id, :departement_code, :region_id)";
        getvParams().addValue("name", site.getName(), Types.VARCHAR);
        getvParams().addValue("description", site.getDescription(), Types.VARCHAR);
        getvParams().addValue("location", site.getLocation(), Types.VARCHAR);
        getvParams().addValue("postcode", site.getPostcode(), Types.VARCHAR);
        getvParams().addValue("latitude", site.getLatitude(), Types.REAL);
        getvParams().addValue("longitude", site.getLongitude(), Types.REAL);
        getvParams().addValue("user_account_id", site.getCreator().getId(), Types.INTEGER);
        getvParams().addValue("departement_code", site.getDepartement().getCode(), Types.VARCHAR);
        getvParams().addValue("region_id", site.getRegion().getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams(), getvKeyHolder(), new String[] {"site_id"});
        site.setId((int) getvKeyHolder().getKey());
    }

    @Override
    public void createSiteTopo(Site site,Topo topo) {
        String vSQL = "INSERT INTO site_topo(site_id, topo_id) VALUES(:site_id, :topo_id)";
        getvParams().addValue("site_id", site.getId(), Types.INTEGER);
        getvParams().addValue("topo_id", topo.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void update(Site site){
        String vSQL = "UPDATE site SET name=:name, description=:description, location=:location, postcode=:postcode, latitude=:latitude, longitude=:longitude, user_account_id=:user_account_id, departement_code=:departement_code, region_id=:region_id WHERE site_id=:id";
        getvParams().addValue("name", site.getName(), Types.VARCHAR);
        getvParams().addValue("description", site.getDescription(), Types.VARCHAR);
        getvParams().addValue("location", site.getLocation(), Types.VARCHAR);
        getvParams().addValue("postcode", site.getPostcode(), Types.VARCHAR);
        getvParams().addValue("latitude", site.getLatitude(), Types.REAL);
        getvParams().addValue("longitude", site.getLongitude(), Types.REAL);
        getvParams().addValue("user_account_id", site.getCreator().getId(), Types.INTEGER);
        getvParams().addValue("departement_code", site.getDepartement().getCode(), Types.VARCHAR);
        getvParams().addValue("region_id", site.getRegion().getId(), Types.INTEGER);
        getvParams().addValue("id", site.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void delete(int id){
        String vSQL = "DELETE FROM site WHERE site_id=:id";
        getvParams().addValue("id", id, Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void deleteSiteTopo(Site site) {
        String vSQL = "DELETE FROM site_topo WHERE site_id=:id";
        getvParams().addValue("id", site.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public Site find(int id){
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, image.*, image.image_id AS imageId, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN image ON image.image_id= user_account.image_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "WHERE 1=1");
        if(id>0)
        {
            vSQL.append("AND site.site_id = :id ORDER BY site.site_id");
            getvParams().addValue("id", id);
        }
        Site site = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), getSiteRM());
        return site;
    }

    @Override
    public List<Site> findAllSites(){
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, image.*, image.image_id AS imageId, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN image ON image.image_id= user_account.image_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "WHERE 1=1 ");
        List<Site> vList = getvJdbcTemplate().query(vSQL.toString(),getSiteRM());
        return vList;
    }

    @Override
    public List<Site> findAllByTopo(Topo topo){
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, image.*, image.image_id AS imageId, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site_topo " +
                "LEFT JOIN site ON site.site_id = site_topo.site_id " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN image ON image.image_id= user_account.image_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "WHERE 1=1 ");
        if(topo != null)
        {
            if(topo.getId()!=0)
            {
                vSQL.append("AND topo_id=:topo_id ORDER BY site.site_id");
                getvParams().addValue("topo_id", topo.getId());
            }
        }
        List<Site> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),getSiteRM());
        return vList;

    }

    @Override
    public List<Site> findAllBySearchCriteria(String sqlStatement){
        StringBuilder vSQL= new StringBuilder(sqlStatement);
        List<Site> vList = getvJdbcTemplate().query(vSQL.toString(),getSiteRM());
        return vList;
    }

    @Override
    public List<Site> findLastTen(){
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, image.*, image.image_id AS imageId, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN image ON image.image_id= user_account.image_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "ORDER BY site.site_id DESC LIMIT 10");
        List<Site> vList = getvJdbcTemplate().query(vSQL.toString(),getSiteRM());
        return vList;
    }

    @Override
    public List<Site> findCreatorSites(User user) {
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, image.*, image.image_id AS imageId, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN image ON image.image_id= user_account.image_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "WHERE 1=1 ");
        if(user != null)
        {
            if(user.getId()!=0)
            {
                vSQL.append("AND site.user_account_id=:user_id ORDER BY site_id");
                getvParams().addValue("user_id", user.getId());
            }
        }
        List<Site> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),getSiteRM());
        return vList;
    }
}
