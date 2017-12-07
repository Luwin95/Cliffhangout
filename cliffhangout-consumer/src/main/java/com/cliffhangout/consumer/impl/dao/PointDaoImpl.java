package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.consumer.contract.dao.PointDao;
import com.cliffhangout.consumer.impl.rowmapper.PointRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PointDaoImpl extends AbstractDaoImpl implements PointDao {
    @Override
    public void create(Point point){
        String vSQL = "INSERT INTO point(name, description, length_id) VALUES(:name, :description, :length_id)";
        getvParams().addValue("name", point.getName(), Types.VARCHAR);
        getvParams().addValue("description", point.getDescription(), Types.VARCHAR);
        getvParams().addValue("length_id", point.getLengthId(), Types.INTEGER);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, getvParams(), getvKeyHolder(), new String[] {"point_id"});
        point.setId((int) getvKeyHolder().getKey());
    }

    @Override
    public void update(Point point){
        String vSQL = "UPDATE point SET name=:name, description=:description, length_id=:length_id WHERE point_id=:id";
        getvParams().addValue("name", point.getName(), Types.VARCHAR);
        getvParams().addValue("description", point.getDescription(), Types.VARCHAR);
        getvParams().addValue("length_id", point.getLengthId(), Types.INTEGER);
        getvParams().addValue("id", point.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void delete(Point point){
        String vSQL = "DELETE FROM point WHERE point_id=:id";
        getvParams().addValue("id", point.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void deleteAllByLength(Length length){
        String vSQL = "DELETE FROM point WHERE length_id=:length_id";
        getvParams().addValue("length_id", length.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public Point find(int id){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM point WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND point_id = :id");
            getvParams().addValue("id", id);
        }
        RowMapper<Point> vRowMapper = new PointRM();
        Point point = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return point;
    }

    @Override
    public List<Point> findAllByLength(Length length){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM point WHERE 1=1 ");
        if(length != null)
        {
            if(length.getId()!=0)
            {
                vSQL.append("AND length_id=:length_id ORDER BY point_id");
                getvParams().addValue("length_id", length.getId());
            }
        }
        RowMapper<Point> vRowMapper = new PointRM();
        List<Point> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }
}
