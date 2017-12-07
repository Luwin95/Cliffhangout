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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LengthDaoImpl extends AbstractDaoImpl implements LengthDao {

    @Override
    public void create(Length length){
        String vSQL = "INSERT INTO length(name, description, way_id) VALUES(:name, :description, :way_id)";
        getvParams().addValue("name", length.getName(), Types.VARCHAR);
        getvParams().addValue("description", length.getDescription(), Types.VARCHAR);
        getvParams().addValue("way_id", length.getWayId());
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams(), getvKeyHolder(), new String[] {"length_id"});
        length.setId((int) getvKeyHolder().getKey());
    }

    @Override
    public void update(Length length) {
        String vSQL = "UPDATE length SET name=:name, description=:description, way_id=:way_id WHERE length_id=:id";
        getvParams().addValue("name", length.getName(), Types.VARCHAR);
        getvParams().addValue("description", length.getDescription(), Types.VARCHAR);
        getvParams().addValue("way_id", length.getWayId());
        getvParams().addValue("id", length.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void delete(Length length){
        String vSQL = "DELETE FROM length WHERE length_id=:id";
        getvParams().addValue("id", length.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void deleteAllByWay(Way way){
        String vSQL = "DELETE FROM length WHERE way_id=:way_id";
        getvParams().addValue("way_id", way.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public Length find(int id){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM length WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND length_id = :id");
            getvParams().addValue("id", id);
        }
        RowMapper<Length> vRowMapper = new LengthRM();
        Length length = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return length;
    }

    @Override
    public List<Length> findAllByWay(Way way){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM length WHERE 1=1 ");
        if(way != null)
        {
            if(way.getId()!=0)
            {
                vSQL.append("AND way_id=:way_id");
                getvParams().addValue("way_id", way.getId());
            }
        }
        RowMapper<Length> vRowMapper = new LengthRM();
        List<Length> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }

}

