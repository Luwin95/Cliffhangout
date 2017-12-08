package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Site;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteRM extends AbstractRM implements RowMapper<Site> {
    private UserRM userRM;
    private DepartementRM departementRM;

    private UserRM getUserRM() {
        return userRM;
    }

    public void setUserRM(UserRM userRM) {
        this.userRM = userRM;
    }

    private DepartementRM getDepartementRM() {
        return departementRM;
    }

    public void setDepartementRM(DepartementRM departementRM) {
        this.departementRM = departementRM;
    }

    @Override
    public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site();
        site.setId(rs.getInt("site_id"));
        site.setName(rs.getString("name"));
        site.setDescription(rs.getString("description"));
        site.setLocation(rs.getString("location"));
        site.setPostcode(rs.getString("postcode"));
        site.setLatitude(rs.getFloat("latitude"));
        site.setLongitude(rs.getFloat("longitude"));
        site.setCreator(getUserRM().mapRow(rs,rowNum));
        site.setDepartement(getDepartementRM().mapRow(rs,rowNum));
        site.setRegion(site.getDepartement().getRegion());
        return site;
    }
}
