package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Way;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WayRM extends AbstractRM implements RowMapper<Way> {

    private QuotationRM quotationRM;

    private QuotationRM getQuotationRM() {
        return quotationRM;
    }

    public void setQuotationRM(QuotationRM quotationRM) {
        this.quotationRM = quotationRM;
    }

    @Override
    public Way mapRow(ResultSet rs, int rowNum) throws SQLException {
        Way way = new Way();
        way.setId(rs.getInt("way_id"));
        way.setName(rs.getString("name"));
        way.setHeight(rs.getDouble("height"));
        way.setPointsNb(rs.getInt("points_nb"));
        way.setSectorId(rs.getInt("sector_id"));
        way.setQuotation(getQuotationRM().mapRow(rs, rowNum));
        return way;
    }
}
