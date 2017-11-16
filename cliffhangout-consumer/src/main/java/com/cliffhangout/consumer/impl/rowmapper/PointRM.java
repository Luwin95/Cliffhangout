package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Point;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PointRM extends AbstractRM implements RowMapper<Point> {
    @Override
    public Point mapRow(ResultSet rs, int rowNum) throws SQLException {
        Point point = new Point();
        point.setId(rs.getInt("id"));
        point.setName(rs.getString("name"));
        point.setDescription(rs.getString("description"));
        point.setLengthId(rs.getInt("length_id"));
        return point;
    }
}
