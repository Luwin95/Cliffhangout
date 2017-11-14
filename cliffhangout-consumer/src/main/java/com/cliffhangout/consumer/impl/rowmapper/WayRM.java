package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.contract.dao.LengthDao;
import com.cliffhangout.consumer.contract.dao.QuotationDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WayRM implements RowMapper<Way> {
    private QuotationDao quotationDao;
    private LengthDao lengthDao;

    public QuotationDao getQuotationDao() {
        return quotationDao;
    }

    public void setQuotationDao(QuotationDao quotationDao) {
        this.quotationDao = quotationDao;
    }

    public LengthDao getLengthDao() {
        return lengthDao;
    }

    public void setLengthDao(LengthDao lengthDao) {
        this.lengthDao = lengthDao;
    }

    @Override
    public Way mapRow(ResultSet rs, int rowNum) throws SQLException {
        Way way = new Way();
        way.setId(rs.getInt("id"));
        way.setName(rs.getString("name"));
        way.setHeight(rs.getDouble("height"));
        way.setPointsNb(rs.getInt("points_nb"));
        way.setSectorId(rs.getInt("sector_id"));
        way.setQuotation(quotationDao.find(rs.getInt("quotation_difficulty")));
        way.setLengths(lengthDao.findAllByWay(way));
        return null;
    }
}
