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
        StringBuilder vSQL= new StringBuilder("SELECT * FROM region WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND region_id = :id");
            getvParams().addValue("id", id);
        }
        RowMapper<Region> vRowMapper = new RegionRM();
        Region region = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return region;
    }

    @Override
    public List<Region> findAll(){
        String vSQL= "SELECT * FROM region ORDER BY region_id";
        RowMapper<Region> vRowMapper = new RegionRM();
        List<Region> vList = getvNamedParameterJdbcTemplate().query(vSQL,getvParams(),vRowMapper);
        return vList;
    }

}
