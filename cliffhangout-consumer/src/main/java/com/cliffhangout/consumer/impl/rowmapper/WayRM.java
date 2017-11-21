package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.contract.dao.LengthDao;
import com.cliffhangout.consumer.contract.dao.QuotationDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WayRM extends AbstractRM implements RowMapper<Way> {

    @Override
    public Way mapRow(ResultSet rs, int rowNum) throws SQLException {
        Way way = new Way();
        way.setId(rs.getInt("way_id"));
        way.setName(rs.getString("name"));
        way.setHeight(rs.getDouble("height"));
        way.setPointsNb(rs.getInt("points_nb"));
        way.setSectorId(rs.getInt("sector_id"));
        QuotationRM quotationRM = new QuotationRM();
        way.setQuotation(quotationRM.mapRow(rs, rowNum));
        return way;
    }
}
