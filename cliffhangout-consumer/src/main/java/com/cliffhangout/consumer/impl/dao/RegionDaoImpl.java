package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Region;
import com.cliffhangout.consumer.contract.dao.RegionDao;
import com.cliffhangout.consumer.impl.rowmapper.RegionRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDaoImpl extends AbstractDaoImpl implements RegionDao {
    @Override
    public Region find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM region WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND region_id = :id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Region> vRowMapper = new RegionRM();
        Region region = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return region;
    }

    @Override
    public List<Region> findAll(){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        String vSQL= "SELECT * FROM region ORDER BY region_id";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Region> vRowMapper = new RegionRM();
        List<Region> vList = vJdbcTemplate.query(vSQL,vParams,vRowMapper);
        return vList;
    }

}
