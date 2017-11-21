package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.contract.dao.PointDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LengthRM extends AbstractRM implements RowMapper<Length> {
    @Override
    public Length mapRow(ResultSet rs, int rowNum) throws SQLException {
        Length length = new Length();
        length.setId(rs.getInt("length_id"));
        length.setName(rs.getString("name"));
        length.setDescription(rs.getString("description"));
        length.setWayId(rs.getInt("way_id"));
        return length;
    }
}
