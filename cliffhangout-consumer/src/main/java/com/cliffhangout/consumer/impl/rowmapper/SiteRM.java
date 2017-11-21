package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Site;
import com.cliffhangout.consumer.contract.dao.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteRM extends AbstractRM implements RowMapper<Site> {

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
        RegionRM  regionRM = new RegionRM();
        UserRM userRM = new UserRM();
        DepartementRM departementRM = new DepartementRM();
        site.setCreator(userRM.mapRow(rs,rowNum));
        site.setDepartement(departementRM.mapRow(rs,rowNum));
        site.setRegion(site.getDepartement().getRegion());
        return site;
    }
}
