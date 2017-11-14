package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.consumer.contract.dao.PointDao;
import com.cliffhangout.consumer.impl.rowmapper.PointRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PointDaoImpl extends AbstractDaoImpl implements PointDao {
    @Override
    public void create(Point point){
        String vSQL = "INSERT INTO point(name, description, length_id) VALUES(:name, :description, :length_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", point.getName(), Types.VARCHAR);
        vParams.addValue("description", point.getDescription(), Types.VARCHAR);
        vParams.addValue("length_id", point.getLengthId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(Point point){
        String vSQL = "UPDATE point SET name=:name, description=:description, length_id=:length_id WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", point.getName(), Types.VARCHAR);
        vParams.addValue("description", point.getDescription(), Types.VARCHAR);
        vParams.addValue("length_id", point.getLengthId(), Types.INTEGER);
        vParams.addValue("id", point.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(Point point){
        String vSQL = "DELETE FROM point WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", point.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void deleteAllByLength(Length length){
        String vSQL = "DELETE FROM point WHERE length_id=:length_id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("length_id", length.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Point find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM point WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND id = :id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Point> vRowMapper = new PointRM();
        Point point = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return point;
    }

    @Override
    public List<Point> findAllByLength(Length length){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM point WHERE 1=1 ");
        if(length != null)
        {
            if(length.getId()!=0)
            {
                vSQL.append("AND length_id=:length_id ORDER BY id");
                vParams.addValue("length_id", length.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Point> vRowMapper = new PointRM();
        List<Point> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }
}
