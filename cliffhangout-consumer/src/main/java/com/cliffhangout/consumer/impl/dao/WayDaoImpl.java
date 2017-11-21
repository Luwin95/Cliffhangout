package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.contract.dao.WayDao;
import com.cliffhangout.consumer.impl.rowmapper.WayRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.List;

public class WayDaoImpl extends AbstractDaoImpl implements WayDao {
    @Override
    public void create(Way way){
        String vSQL = "INSERT INTO way(name, height, quotation_difficulty, points_nb, sector_id) VALUES(:name, :height, :quotation_difficulty, :points_nb, : sector_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", way.getName(), Types.VARCHAR);
        vParams.addValue("height", way.getHeight(), Types.REAL);
        vParams.addValue("quotation_difficulty", way.getQuotation().getDifficulty(), Types.INTEGER);
        vParams.addValue("points_nb", way.getPointsNb(), Types.INTEGER);
        vParams.addValue("sector_id", way.getSectorId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(Way way){
        String vSQL = "UPDATE way SET name=:name, height=:height, quotation_difficulty=:quotation_difficulty, points_nb=:points_nb, sector_id=:sector_id WHERE way_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", way.getName(), Types.VARCHAR);
        vParams.addValue("height", way.getHeight(), Types.REAL);
        vParams.addValue("quotation_difficulty", way.getQuotation().getDifficulty(), Types.INTEGER);
        vParams.addValue("points_nb", way.getPointsNb(), Types.INTEGER);
        vParams.addValue("sector_id", way.getSectorId(), Types.INTEGER);
        vParams.addValue("id", way.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(Way way){
        String vSQL = "DELETE FROM way WHERE way_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", way.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void deleteAllBySector(Sector sector){
        String vSQL = "DELETE FROM way WHERE sector_id=:sector_id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("sector_id", sector.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Way find(int id, Sector sector){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM way WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND way_id = :id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Way> vRowMapper = new WayRM();
        Way way = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return way;
    }

    @Override
    public List<Way> findAllBySector(Sector sector){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM way LEFT JOIN quotation ON quotation.quotation_difficulty = way.quotation_difficulty WHERE 1=1 ");
        if(sector != null)
        {
            if(sector.getId()!=0)
            {
                vSQL.append("AND sector_id=:sector_id ORDER BY way_id");
                vParams.addValue("sector_id", sector.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Way> vRowMapper = new WayRM();
        List<Way> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }
}


