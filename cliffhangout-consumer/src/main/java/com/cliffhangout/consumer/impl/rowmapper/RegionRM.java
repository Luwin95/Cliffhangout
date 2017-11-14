package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Region;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionRM implements RowMapper<Region> {
    @Override
    public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
        Region vRegion = new Region();
        vRegion.setId(rs.getInt("region_id"));
        vRegion.setName(rs.getString("region_name"));
        return vRegion;
    }
}
