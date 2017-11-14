package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.consumer.contract.dao.WayDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorRM implements RowMapper<Sector> {
    private WayDao wayDao;

    public WayDao getWayDao() {
        return wayDao;
    }

    public void setWayDao(WayDao wayDao) {
        this.wayDao = wayDao;
    }

    @Override
    public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sector sector = new Sector();
        sector.setId(rs.getInt("id"));
        sector.setName(rs.getString("name"));
        sector .setDescription(rs.getString("description"));
        sector.setSiteId(rs.getInt("site_id"));
        sector.setWays(wayDao.findAllBySector(sector));
        return sector;
    }
}
