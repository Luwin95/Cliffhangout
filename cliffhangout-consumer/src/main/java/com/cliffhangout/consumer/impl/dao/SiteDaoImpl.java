package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.*;
import com.cliffhangout.consumer.impl.rowmapper.SiteRM;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class SiteDaoImpl extends AbstractDaoImpl implements SiteDao {

    @Override
    public void create(Site site){
        String vSQL = "INSERT INTO site(name, description, location, postcode, latitude, longitude, user_account_id, departement_code, region_id) VALUES(:name, :description, :location, :postcode, :latitude, :longitude, :user_account_id, :departement_code, :region_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", site.getName(), Types.VARCHAR);
        vParams.addValue("description", site.getDescription(), Types.VARCHAR);
        vParams.addValue("location", site.getLocation(), Types.VARCHAR);
        vParams.addValue("postcode", site.getPostcode(), Types.VARCHAR);
        vParams.addValue("latitude", site.getLatitude(), Types.REAL);
        vParams.addValue("longitude", site.getLongitude(), Types.REAL);
        vParams.addValue("user_account_id", site.getCreator().getId(), Types.INTEGER);
        vParams.addValue("departement_code", site.getDepartement().getCode(), Types.VARCHAR);
        vParams.addValue("region_id", site.getRegion().getId(), Types.INTEGER);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] {"site_id"});
        site.setId((int) keyHolder.getKey());
    }

    @Override
    public void createSiteTopo(Site site,Topo topo) {
        String vSQL = "INSERT INTO site_topo(site_id, topo_id) VALUES(:site_id, :topo_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", site.getId(), Types.INTEGER);
        vParams.addValue("topo_id", topo.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(Site site){
        String vSQL = "UPDATE site SET name=:name, description=:description, location=:location, postcode=:postcode, latitude=:latitude, longitude=:longitude, user_account_id=:user_account_id, departement_code=:departement_code, region_id=:region_id WHERE site_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", site.getName(), Types.VARCHAR);
        vParams.addValue("description", site.getDescription(), Types.VARCHAR);
        vParams.addValue("location", site.getLocation(), Types.VARCHAR);
        vParams.addValue("postcode", site.getPostcode(), Types.VARCHAR);
        vParams.addValue("latitude", site.getLatitude(), Types.REAL);
        vParams.addValue("longitude", site.getLongitude(), Types.REAL);
        vParams.addValue("user_account_id", site.getCreator().getId(), Types.INTEGER);
        vParams.addValue("departement_code", site.getDepartement().getCode(), Types.VARCHAR);
        vParams.addValue("region_id", site.getRegion().getId(), Types.INTEGER);
        vParams.addValue("id", site.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(int id){
        String vSQL = "DELETE FROM site WHERE site_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", id, Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void deleteSiteTopo(Site site) {
        String vSQL = "DELETE FROM site_topo WHERE site_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", site.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Site find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, user_account.user_account_id AS user_id, region.region_name, departement.departement_name from site " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "WHERE 1=1");
        if(id>0)
        {
            vSQL.append("AND site.site_id = :id ORDER BY site.site_id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        Site site = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return site;
    }

    @Override
    public List<Site> findAllSites(){
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "WHERE 1=1 ");
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        List<Site> vList = vJdbcTemplate.query(vSQL.toString(),vRowMapper);
        return vList;
    }

    @Override
    public List<Site> findAllByTopo(Topo topo){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site_topo " +
                "LEFT JOIN site ON site.site_id = site_topo.site_id " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "WHERE 1=1 ");
        if(topo != null)
        {
            if(topo.getId()!=0)
            {
                vSQL.append("AND topo_id=:topo_id ORDER BY site.site_id");
                vParams.addValue("topo_id", topo.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        List<Site> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;

    }

    @Override
    public List<Site> findAllBySearchCriteria(String sqlStatement){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder(sqlStatement);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        List<Site> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }

    @Override
    public List<Site> findLastTen(){
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "ORDER BY site.site_id DESC LIMIT 10");
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        List<Site> vList = vJdbcTemplate.query(vSQL.toString(),vRowMapper);
        return vList;
    }

    @Override
    public List<Site> findCreatorSites(User user) {
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT site.*, user_account.*, user_account.user_account_id AS user_id, region.region_name, departement.departement_name FROM site " +
                "LEFT JOIN user_account ON site.user_account_id = user_account.user_account_id " +
                "LEFT JOIN region ON site.region_id=region.region_id " +
                "LEFT JOIN departement ON site.departement_code = departement.departement_code " +
                "WHERE 1=1 ");
        if(user != null)
        {
            if(user.getId()!=0)
            {
                vSQL.append("AND site.user_account_id=:user_id ORDER BY site_id");
                vParams.addValue("user_id", user.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        List<Site> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }
}
