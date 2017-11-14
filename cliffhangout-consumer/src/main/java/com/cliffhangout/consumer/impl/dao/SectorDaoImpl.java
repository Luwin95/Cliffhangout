package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.contract.dao.SectorDao;
import com.cliffhangout.consumer.contract.dao.WayDao;
import com.cliffhangout.consumer.impl.rowmapper.SectorRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorDaoImpl extends AbstractDaoImpl implements SectorDao {

    @Override
    public void create(Sector sector){
        String vSQL = "INSERT INTO sector(name, description, site_id) VALUES(:name, :description, :site_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", sector.getName(), Types.VARCHAR);
        vParams.addValue("description", sector.getDescription(), Types.VARCHAR);
        vParams.addValue("site_id", sector.getSiteId());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(Sector sector){
        String vSQL = "UPDATE sector SET name=:name, description=:description, site_id=:site_id WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", sector.getName(), Types.VARCHAR);
        vParams.addValue("description", sector.getDescription(), Types.VARCHAR);
        vParams.addValue("site_id", sector.getSiteId());
        vParams.addValue("id", sector.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(Sector sector){
        String vSQL = "DELETE FROM sector WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", sector.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void deleteAllBySite(Site site){
        String vSQL = "DELETE FROM sector WHERE site_id=:site_id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", site.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Sector find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM sector WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND id = :id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Sector> vRowMapper = new SectorRM();
        Sector sector = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return sector;
    }

    @Override
    public List<Sector> findAllBySite(Site site){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM sector WHERE 1=1 ");
        if(site != null)
        {
            if(site.getId()!=0)
            {
                vSQL.append("AND site_id=:site_id");
                vParams.addValue("site_id", site.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Sector> vRowMapper = new SectorRM();
        List<Sector> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }
}
