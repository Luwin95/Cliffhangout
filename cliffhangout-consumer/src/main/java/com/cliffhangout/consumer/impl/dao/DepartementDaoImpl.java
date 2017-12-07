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
        StringBuilder vSQL= new StringBuilder("SELECT * FROM departement LEFT JOIN region ON region.region_id = departement.region_id WHERE 1=1 ");
        if(code != null && !code.equals(""))
        {
            vSQL.append("AND departement_code=:code");
            getvParams().addValue("code", code);
        }
        RowMapper<Departement> vRowMapper = new DepartementRM();
        Departement departement= getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return departement;
    }

    @Override
    public List<Departement> findAll(){
        String vSQL= "SELECT * FROM departement LEFT JOIN region ON region.region_id = departement.region_id  ORDER BY departement_code";
        RowMapper<Departement> vRowMapper = new DepartementRM();
        List<Departement> vList = getvNamedParameterJdbcTemplate().query(vSQL,getvParams(),vRowMapper);
        return vList;
    }
}
