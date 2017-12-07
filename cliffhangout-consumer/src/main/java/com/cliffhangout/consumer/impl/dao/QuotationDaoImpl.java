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
        StringBuilder vSQL= new StringBuilder("SELECT * FROM quotation WHERE 1=1 ");
        if(difficulty>0)
        {
            vSQL.append("AND  quotation_difficulty=:quotation_difficulty");
            getvParams().addValue("quotation_difficulty", difficulty);
        }
        RowMapper<Quotation> vRowMapper = new QuotationRM();
        Quotation quotation= getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return quotation;

    }

    @Override
    public Quotation findByName(String name){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM quotation WHERE 1=1 ");
        if(name != null)
        {
            if(!name.equals(""))
            {
                vSQL.append("AND quotation_name=:quotation_name");
                getvParams().addValue("quotation_name", name);
            }
        }
        RowMapper<Quotation> vRowMapper = new QuotationRM();
        Quotation quotation= getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return quotation;
    }

}
