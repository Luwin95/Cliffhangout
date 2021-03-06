package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Departement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartementRM extends AbstractRM implements RowMapper<Departement> {
    private RegionRM regionRM;

    private RegionRM getRegionRM() {
        return regionRM;
    }

    public void setRegionRM(RegionRM regionRM) {
        this.regionRM = regionRM;
    }

    @Override
    public Departement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Departement departement = new Departement();
        departement.setCode(rs.getString("departement_code"));
        departement.setName(rs.getString("departement_name"));
        departement.setRegion(getRegionRM().mapRow(rs, rowNum));
        return departement;
    }
}
