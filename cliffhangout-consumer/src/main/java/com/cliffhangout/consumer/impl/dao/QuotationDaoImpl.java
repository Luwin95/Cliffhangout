package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Quotation;
import com.cliffhangout.consumer.contract.dao.QuotationDao;
import com.cliffhangout.consumer.impl.rowmapper.QuotationRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuotationDaoImpl extends AbstractDaoImpl implements QuotationDao {
    @Override
    public Quotation find(int difficulty){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM quotation WHERE 1=1 ");
        if(difficulty>0)
        {
            vSQL.append("AND  quotation_difficulty=:quotation_difficulty");
            vParams.addValue("quotation_difficulty", difficulty);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Quotation> vRowMapper = new QuotationRM();
        Quotation quotation= vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return quotation;

    }

    @Override
    public Quotation findByName(String name){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM quotation WHERE 1=1 ");
        if(name != null)
        {
            if(!name.equals(""))
            {
                vSQL.append("AND quotation_name=:quotation_name");
                vParams.addValue("quotation_name", name);
            }

        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Quotation> vRowMapper = new QuotationRM();
        Quotation quotation= vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return quotation;
    }

}
