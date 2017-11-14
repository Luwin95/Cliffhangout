package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Departement;
import com.cliffhangout.consumer.contract.dao.DepartementDao;
import com.cliffhangout.consumer.contract.dao.RegionDao;
import com.cliffhangout.consumer.impl.rowmapper.DepartementRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementDaoImpl extends AbstractDaoImpl implements DepartementDao {

    @Override
    public Departement find(String code){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM departement WHERE 1=1 ");
        if(code != null && !code.equals(""))
        {
            vSQL.append("AND departement_code=:code");
            vParams.addValue("code", code);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Departement> vRowMapper = new DepartementRM();
        Departement departement= vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return departement;
    }

    @Override
    public List<Departement> findAll(){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        String vSQL= "SELECT * FROM departement ORDER BY departement_code";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Departement> vRowMapper = new DepartementRM();
        List<Departement> vList = vJdbcTemplate.query(vSQL,vParams,vRowMapper);
        return vList;
    }
}
