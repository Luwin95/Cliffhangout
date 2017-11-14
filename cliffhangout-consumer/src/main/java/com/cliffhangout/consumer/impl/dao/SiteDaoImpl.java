package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.*;
import com.cliffhangout.consumer.impl.rowmapper.SiteRM;
import com.sun.deploy.security.ValidationState;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SiteDaoImpl extends AbstractDaoImpl implements SiteDao {

    @Override
    public void create(Site site){
        String vSQL = "INSERT INTO site(name, description, location, postcode, latitude, longitude, user_account_id, departement_code, region_id) VALUES(:name, :description, :location, :postcode, :latitude, :longitude, :user_account_id, :department_code, :region_id)";
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
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(Site site){
        String vSQL = "UPDATE site SET name=:name, description=:description, location=:location, postcode=:postcode, latitude=:latitude, longitude=:longitude, user_account_id=:user_account_id, departement_code=:departement_code, region_id=:region_id WHERE id=:id";
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
    public void delete(Site site){
        String vSQL = "DELETE FROM site WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", site.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Site find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM site WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND id = :id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        Site site = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return site;
    }

    @Override
    public List<Site> findAllSites(){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM site ORDER BY id");
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        List<Site> vList = vJdbcTemplate.query(vSQL.toString(),vRowMapper);
        return vList;
    }

    @Override
    public List<Site> findAllByTopo(Topo topo){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM site_topo LEFT JOIN site on site.id = site_topo.site_id WHERE 1=1 ");
        if(topo != null)
        {
            if(topo.getId()!=0)
            {
                vSQL.append("AND topo_id=:topo_id ORDER BY id");
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
        StringBuilder vSQL= new StringBuilder("SELECT * FROM site ORDER BY id DESC LIMIT 10");
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        RowMapper<Site> vRowMapper = new SiteRM();
        List<Site> vList = vJdbcTemplate.query(vSQL.toString(),vRowMapper);
        return vList;
    }

}
