package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Quotation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationRM extends AbstractRM implements RowMapper<Quotation> {
    @Override
    public Quotation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quotation quotation = new Quotation();
        quotation.setName(rs.getString("quotation_name"));
        quotation.setDifficulty(rs.getInt("quotation_difficulty"));
        return quotation;
    }
}
