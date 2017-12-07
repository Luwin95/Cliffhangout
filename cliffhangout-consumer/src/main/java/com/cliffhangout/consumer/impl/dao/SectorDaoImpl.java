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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorDaoImpl extends AbstractDaoImpl implements SectorDao {

    @Override
    public void create(Sector sector){
        String vSQL = "INSERT INTO sector(name, description, site_id) VALUES(:name, :description, :site_id)";
        getvParams().addValue("name", sector.getName(), Types.VARCHAR);
        getvParams().addValue("description", sector.getDescription(), Types.VARCHAR);
        getvParams().addValue("site_id", sector.getSiteId());
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams(), getvKeyHolder(), new String[] {"sector_id"});
        sector.setId((int) getvKeyHolder().getKey());
    }

    @Override
    public void update(Sector sector){
        String vSQL = "UPDATE sector SET name=:name, description=:description, site_id=:site_id WHERE sector_id=:id";
        getvParams().addValue("name", sector.getName(), Types.VARCHAR);
        getvParams().addValue("description", sector.getDescription(), Types.VARCHAR);
        getvParams().addValue("site_id", sector.getSiteId());
        getvParams().addValue("id", sector.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void delete(Sector sector){
        String vSQL = "DELETE FROM sector WHERE sector_id=:id";
        getvParams().addValue("id", sector.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void deleteAllBySite(Site site){
        String vSQL = "DELETE FROM sector WHERE site_id=:site_id";
        getvParams().addValue("site_id", site.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public Sector find(int id){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM sector WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND sector_id = :id");
            getvParams().addValue("id", id);
        }
        RowMapper<Sector> vRowMapper = new SectorRM();
        Sector sector = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return sector;
    }

    @Override
    public List<Sector> findAllBySite(Site site){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM sector WHERE 1=1 ");
        if(site != null)
        {
            if(site.getId()!=0)
            {
                vSQL.append("AND site_id=:site_id ORDER BY sector_id");
                getvParams().addValue("site_id", site.getId());
            }
        }
        RowMapper<Sector> vRowMapper = new SectorRM();
        List<Sector> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }
}
