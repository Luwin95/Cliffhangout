package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.contract.dao.LengthDao;
import com.cliffhangout.consumer.contract.dao.PointDao;
import com.cliffhangout.consumer.impl.rowmapper.LengthRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LengthDaoImpl extends AbstractDaoImpl implements LengthDao {

    @Override
    public void create(Length length){
        String vSQL = "INSERT INTO length(name, description, way_id) VALUES(:name, :description, :way_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", length.getName(), Types.VARCHAR);
        vParams.addValue("description", length.getDescription(), Types.VARCHAR);
        vParams.addValue("way_id", length.getWayId());
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(Length length) {
        String vSQL = "UPDATE length SET name=:name, description=:description, way_id=:way_id WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", length.getName(), Types.VARCHAR);
        vParams.addValue("description", length.getDescription(), Types.VARCHAR);
        vParams.addValue("way_id", length.getWayId());
        vParams.addValue("id", length.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(Length length){
        String vSQL = "DELETE FROM length WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", length.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void deleteAllByWay(Way way){
        String vSQL = "DELETE FROM length WHERE way_id=:way_id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("way_id", way.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Length find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM length WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND id = :id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Length> vRowMapper = new LengthRM();
        Length length = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return length;
    }

    @Override
    public List<Length> findAllByWay(Way way){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM length WHERE 1=1 ");
        if(way != null)
        {
            if(way.getId()!=0)
            {
                vSQL.append("AND way_id=:way_id");
                vParams.addValue("way_id", way.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Length> vRowMapper = new LengthRM();
        List<Length> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }

}

